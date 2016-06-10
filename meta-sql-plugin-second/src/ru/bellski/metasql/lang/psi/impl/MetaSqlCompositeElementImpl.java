package ru.bellski.metasql.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.lang.psi.MetaSqlCompositeElement;

/**
 * Created by oem on 6/2/16.
 */
public class MetaSqlCompositeElementImpl extends ASTWrapperPsiElement implements MetaSqlCompositeElement {

    public MetaSqlCompositeElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
