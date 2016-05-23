package ru.bellski.metasql.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.lang.psi.ReturnRuleClause;

/**
 * Created by oem on 5/23/16.
 */
public class ReturnRuleClauseImpl extends MetaSqlCompositeElementImpl implements ReturnRuleClause {

    public ReturnRuleClauseImpl(@NotNull ASTNode node) {
        super(node);
    }
}
