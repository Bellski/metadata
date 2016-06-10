package ru.bellski.metasql.lang.psi.impl;

import com.intellij.codeInsight.completion.JavaPsiClassReferenceElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bellski.metasql.lang.SqlMetadataJavaClassCache;
import ru.bellski.metasql.lang.psi.MetaSqlMetadataReferenceElement;

import java.util.stream.Collectors;

/**
 * Created by oem on 6/8/16.
 */
public class MetaSqlMetadataReferenceElementImpl extends MetaSqlReferenceElementImpl implements MetaSqlMetadataReferenceElement {

    public MetaSqlMetadataReferenceElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        final String text = getText();
        PsiElement metadata = null;

        if (text != null) {
            final int lastDot = text.lastIndexOf('.');
            if (lastDot > 0) {
                PsiClass[] result = PsiShortNamesCache
                        .getInstance(getProject())
                        .getClassesByName(text.substring(lastDot + 1), GlobalSearchScope.allScope(getProject()));

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
    public Object[] getVariants() {
        return SqlMetadataJavaClassCache
                .getInstance(getProject())
                .getAllSqlMetaClasses()
                .stream()
                .map(JavaPsiClassReferenceElement::new)
                .collect(Collectors.toList()).toArray();
    }

    @Override
    public PsiReference getReference() {
        return this;
    }
}
