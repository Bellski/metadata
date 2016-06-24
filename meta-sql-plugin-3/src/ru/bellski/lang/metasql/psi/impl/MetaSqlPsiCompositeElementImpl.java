package ru.bellski.lang.metasql.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import ru.bellski.lang.metasql.psi.MetaSqlPsiCompositeElement;

/**
 * Created by oem on 6/24/16.
 */
public class MetaSqlPsiCompositeElementImpl extends ASTWrapperPsiElement implements MetaSqlPsiCompositeElement {

    public MetaSqlPsiCompositeElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public IElementType getTokenType() {
        return getNode().getElementType();
    }

    public String toString() {
        return getTokenType().toString();
    }
}
