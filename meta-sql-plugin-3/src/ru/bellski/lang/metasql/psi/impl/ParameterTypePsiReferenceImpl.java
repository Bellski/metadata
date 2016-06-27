package ru.bellski.lang.metasql.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bellski.lang.metasql.BaseTypesSupported;
import ru.bellski.lang.metasql.psi.ParameterTypePsiReference;

/**
 * Created by oem on 6/27/16.
 */
public class ParameterTypePsiReferenceImpl extends MetaSqlPsiReferenceImpl implements ParameterTypePsiReference {

    public ParameterTypePsiReferenceImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        final PsiElement resolvedClass = super.resolve();

        if (resolvedClass == null) {
            return null;
        }

        if (BaseTypesSupported.supported(getReferenceName()) && !BaseTypesSupported.ARRAY_LIST.equals(getReferenceName())) {
            return resolvedClass;
        }

        return null;
    }

    @Override
    public PsiReference getReference() {
        return this;
    }
}
