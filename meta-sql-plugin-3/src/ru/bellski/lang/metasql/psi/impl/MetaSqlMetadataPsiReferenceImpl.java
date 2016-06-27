package ru.bellski.lang.metasql.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bellski.lang.metasql.psi.MetaSqlMetadataPsiReference;

/**
 * Created by Aleksandr on 26.06.2016.
 */
public class MetaSqlMetadataPsiReferenceImpl extends MetaSqlPsiReferenceImpl implements MetaSqlMetadataPsiReference{

    public MetaSqlMetadataPsiReferenceImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        final PsiClass resolvedClass = (PsiClass) super.resolve();

        if (resolvedClass == null) return null;
        if (resolvedClass.getModifierList() == null) return null;

        final PsiAnnotation[] annotations = resolvedClass.getModifierList().getAnnotations();

        if (annotations.length == 0) return null;

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

    @Override
    public PsiReference getReference() {
        return this;
    }
}
