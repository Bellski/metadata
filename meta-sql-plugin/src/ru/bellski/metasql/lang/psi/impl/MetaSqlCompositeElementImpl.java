package ru.bellski.metasql.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.lang.psi.MetaSqlElement;
import ru.bellski.metasql.lang.psi.MetaSqlVisitor;

/**
 * Created by oem on 5/23/16.
 */
public class MetaSqlCompositeElementImpl extends ASTWrapperPsiElement implements MetaSqlElement {

    public MetaSqlCompositeElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
