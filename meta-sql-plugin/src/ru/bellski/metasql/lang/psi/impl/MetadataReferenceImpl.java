package ru.bellski.metasql.lang.psi.impl;

import com.intellij.codeInsight.completion.JavaPsiClassReferenceElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.impl.source.resolve.ResolveClassUtil;
import com.intellij.psi.infos.CandidateInfo;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.psi.util.PsiClassUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bellski.metasql.SqlMetadataJavaClassCache;
import ru.bellski.metasql.lang.psi.MetadataReference;

import java.util.stream.Collectors;

/**
 * Created by Aleksandr on 21.05.2016.
 */
public class MetadataReferenceImpl extends MetaSqlCompositeElementImpl implements MetadataReference {

    public MetadataReferenceImpl(@NotNull ASTNode node) {
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
        final String text = getText();
        PsiElement metadata = null;

        if (text != null) {
            final int lastDot = text.lastIndexOf('.');
            if (lastDot > 0) {
                PsiClass[] result = PsiShortNamesCache.getInstance(getProject()).getClassesByName(text.substring(lastDot + 1), GlobalSearchScope.allScope(getProject()));

                for (PsiClass psiClass : result) {
                    if (psiClass.getQualifiedName().equals(getText())) {
                        metadata = psiClass;
                        break;
                    }
                }
            }
        }

        return metadata;
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
        return SqlMetadataJavaClassCache.getInstance(getProject()).getAllSqlMetaClasses().stream().map(JavaPsiClassReferenceElement::new).collect(Collectors.toList()).toArray();
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
