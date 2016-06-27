package ru.bellski.lang.metasql.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.resolve.ClassResolverProcessor;
import com.intellij.psi.impl.source.tree.TreeElement;
import com.intellij.psi.scope.util.PsiScopesUtil;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bellski.lang.metasql.MetaSqlTokenTypes;
import ru.bellski.lang.metasql.psi.*;

/**
 * Created by oem on 6/24/16.
 */
public class MetaSqlPsiReferenceImpl extends MetaSqlPsiCompositeElementImpl implements MetaSqlPsiReference {

    public MetaSqlPsiReferenceImpl(@NotNull ASTNode node) {
        super(node);
    }


    @Override
    public PsiElement getElement() {
        return this;
    }

    @Override
    public TextRange getRangeInElement() {
        final TextRange textRange = getTextRange();
        return new TextRange(0, textRange.getEndOffset() - textRange.getStartOffset());
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        final PsiElement classNameElement = findChildByType(MetaSqlTokenTypes.IDENTIFIER);
        if ((classNameElement == null)) return null;

        final MetaSqlImportList importList = PsiTreeUtil.findChildOfType(getContainingFile(), MetaSqlImportList.class);

        if (importList == null) return null;

        for (MetaSqlImportStatement metaSqlImportStatement : importList.getImportStatementList()) {
            final MetaSqlImportReferenceElement importReferenceElement = metaSqlImportStatement.getImportReferenceElement();

            if (importReferenceElement != null) {
                final String id = importReferenceElement.getText();

                if (classNameElement.getText().equals(id.substring(id.lastIndexOf('.') + 1))) {
                    return JavaPsiFacade.getInstance(getProject()).findClass(id, GlobalSearchScope.allScope(getProject()));
                }
            }
        }

        return null;
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
