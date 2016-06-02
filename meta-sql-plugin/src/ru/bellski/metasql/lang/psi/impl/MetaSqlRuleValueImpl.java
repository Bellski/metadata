package ru.bellski.metasql.lang.psi.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.lang.psi.MetaSqlRuleValue;

/**
 * Created by Aleksandr on 31.05.2016.
 */
public class MetaSqlRuleValueImpl extends MetaSqlCompositeElementImpl implements MetaSqlRuleValue {
    public MetaSqlRuleValueImpl(@NotNull ASTNode node) {
        super(node);
    }
}
