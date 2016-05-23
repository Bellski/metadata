package ru.bellski.metasql.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.lang.psi.MetaSqlVisitor;
import ru.bellski.metasql.lang.psi.MetadataClause;

/**
 * Created by oem on 5/23/16.
 */
public class MetadataClauseImpl extends MetaSqlCompositeElementImpl implements MetadataClause {
    public MetadataClauseImpl(@NotNull ASTNode node) {
        super(node);
    }


    @Override
    public void acceptChildren(MetaSqlVisitor visitor) {
        super.acceptChildren(visitor);
    }
}
