package ru.bellski.metasql.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bellski.metasql.lang.psi.MetaSqlReturn;

/**
 * Created by oem on 6/21/16.
 */
public class MetaSqlReturnImpl extends MetaSqlReferenceElementImpl implements MetaSqlReturn {

    public MetaSqlReturnImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        return null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }
}
