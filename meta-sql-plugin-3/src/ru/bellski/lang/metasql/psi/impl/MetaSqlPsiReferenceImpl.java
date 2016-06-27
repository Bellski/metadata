package ru.bellski.lang.metasql.psi.impl;

import com.google.common.collect.Sets;
import com.intellij.lang.ASTNode;
import com.intellij.lang.FileASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bellski.lang.metasql.MetaSqlTokenTypes;
import ru.bellski.lang.metasql.psi.MetaSqlImportList;
import ru.bellski.lang.metasql.psi.MetaSqlImportReferenceElement;
import ru.bellski.lang.metasql.psi.MetaSqlImportStatement;
import ru.bellski.lang.metasql.psi.MetaSqlPsiReference;

import java.util.Set;

/**
 * Created by oem on 6/24/16.
 */
public class MetaSqlPsiReferenceImpl extends MetaSqlPsiCompositeElementImpl implements MetaSqlPsiReference {
    private enum ReferenceType {
        IMPORT, CLASS_OBJECT, PARAMETER_VARIABLE, METADATA, UNKNOWN
    }


    public MetaSqlPsiReferenceImpl(@NotNull ASTNode node) {
        super(node);
    }


    @Override
    public PsiElement getElement() {
        return this;
    }

    @Override
    public TextRange getRangeInElement() {
        TextRange rangeInElement = null;

        if (getNode().getElementType() == MetaSqlTokenTypes.IMPORT_REFERENCE_ELEMENT) {
            final TextRange textRange = getTextRange();
            rangeInElement = new TextRange(0, textRange.getEndOffset() - textRange.getStartOffset());
        } else {
            final PsiElement id = findChildByType(MetaSqlTokenTypes.IDENTIFIER);

            if (id != null) {
                final int startOffset = id.getStartOffsetInParent();
                rangeInElement = new TextRange(startOffset, startOffset + id.getTextLength());
            }
        }

        return rangeInElement == null ? new TextRange(0, getTextLength()) : rangeInElement;
    }

    @Override
    public int getTextOffset() {
        ASTNode refName;

        if (getNode().getElementType() == MetaSqlTokenTypes.IMPORT_REFERENCE_ELEMENT) {
            refName = this.getNode();
        } else {
            refName = findChildByType(MetaSqlTokenTypes.IDENTIFIER);
        }

        return refName != null ? refName.getStartOffset() : super.getTextOffset();
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        PsiElement resolvedClass = null;

        final String className = getReferenceName();

        switch (getReferenceType()) {
        case IMPORT:
            resolvedClass = resolveImport(className);
            break;
        case CLASS_OBJECT:
            resolvedClass = resolveSimpleClassName(className);
            break;
        case PARAMETER_VARIABLE:
            resolvedClass = resolveSimpleClassNameExclude(className, Sets.newHashSet("List"));
            break;
        case METADATA:
            resolvedClass = resolveMetadata(className);
            break;
        case UNKNOWN:
            resolvedClass = null;
            break;
        }

        return resolvedClass;
    }

    private ReferenceType getReferenceType() {
        ReferenceType type;

        final PsiElement parent = getParent();
        if (parent == null) {
            type = ReferenceType.UNKNOWN;
        } else
            if (parent.getParent() != null) {
                IElementType ownerType = parent.getNode().getElementType();

                if (ownerType == MetaSqlTokenTypes.IMPORT_STATEMENT) {
                    type = ReferenceType.IMPORT;
                } else {
                    ownerType = parent.getParent().getNode().getElementType();

                    if (ownerType == MetaSqlTokenTypes.PARAMETER_VARIABLE) {
                        type = ReferenceType.PARAMETER_VARIABLE;
                    } else
                        if (ownerType == MetaSqlTokenTypes.METADATA_ASSIGN) {
                            type = ReferenceType.METADATA;
                        } else {
                            type = ReferenceType.CLASS_OBJECT;
                        }
                }
            } else {
                type = ReferenceType.UNKNOWN;
            }

        return type;
    }

    private PsiElement resolveImport(String className) {
        return JavaPsiFacade.getInstance(getProject()).findClass(className, GlobalSearchScope.allScope(getProject()));
    }

    private PsiElement resolveSimpleClassNameExclude(String className, Set<String> excludes) {
        PsiElement psiElement = null;

        if (!excludes.contains(getText())) {
            psiElement = resolveSimpleClassName(className);
        }

        return psiElement;
    }

    private PsiElement resolveSimpleClassName(String className) {
        PsiElement resolvedClass = null;

        final FileASTNode metaSqlFile = getContainingFile().getNode();
        final ASTNode importListNode = metaSqlFile.findChildByType(MetaSqlTokenTypes.IMPORT_LIST);

        if (importListNode != null) {
            final MetaSqlImportList importList = (MetaSqlImportList) importListNode.getPsi();

            for (MetaSqlImportStatement metaSqlImportStatement : importList.getImportStatementList()) {
                if (metaSqlImportStatement != null) {
                    final MetaSqlImportReferenceElement importRefElement = metaSqlImportStatement.getImportReferenceElement();

                    if (importRefElement != null) {
                        final String id = importRefElement.getText();

                        if (className.equals(id.substring(id.lastIndexOf('.') + 1))) {
                            resolvedClass = JavaPsiFacade.getInstance(getProject()).findClass(id, GlobalSearchScope.allScope(getProject()));
                        }
                    }
                }
            }
        }

        return resolvedClass;
    }

    private PsiElement resolveMetadata(String className) {
        final PsiClass resolvedClass = (PsiClass) resolveSimpleClassName(className);

        if (resolvedClass == null)
            return null;
        if (resolvedClass.getModifierList() == null)
            return null;

        final PsiAnnotation[] annotations = resolvedClass.getModifierList().getAnnotations();

        if (annotations.length == 0)
            return null;

        boolean annotationFound = false;

        for (PsiAnnotation annotation : annotations) {
            final String qfn = annotation.getQualifiedName();

            if ("ru.bellski.metadata.SqlMetadata".equals(qfn)) {
                annotationFound = true;
                break;
            }
        }

        return annotationFound ? resolvedClass : null;
    }

    protected String getReferenceName() {
        String className = null;

        if (this instanceof MetaSqlImportReferenceElement) {
            className = getText();
        } else {
            final PsiElement classNameElement = findChildByType(MetaSqlTokenTypes.IDENTIFIER);
            className = classNameElement == null ? getText() : classNameElement.getText();
        }

        return className;
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        return ResolveResult.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getCanonicalText() {
        return getText();
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        return this;
    }

    @Override
    public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
        return this;
    }

    @Override
    public boolean isReferenceTo(PsiElement element) {
        return false;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }

    @Override
    public boolean isSoft() {
        return false;
    }

    @Override
    public PsiReference getReference() {
        return this;
    }
}
