package ru.bellski.metasql.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.lang.psi.ReturnRuleValue;

/**
 * Created by oem on 5/23/16.
 */
public class ReturnRuleValueImpl extends MetaSqlCompositeElementImpl implements ReturnRuleValue {
    public ReturnRuleValueImpl(@NotNull ASTNode node) {
        super(node);
    }
}
