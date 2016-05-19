package ru.bellski.metasql.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bellski.metasql.lang.psi.MetaSqlMetadataElement;

/**
 * Created by oem on 5/19/16.
 */
public abstract class MetaSqlMetadataElementImpl extends ASTWrapperPsiElement implements MetaSqlMetadataElement {

    public MetaSqlMetadataElementImpl(@NotNull ASTNode node) {
        super(node);
    }


    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        return this;
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException {
        return this;
    }


}
