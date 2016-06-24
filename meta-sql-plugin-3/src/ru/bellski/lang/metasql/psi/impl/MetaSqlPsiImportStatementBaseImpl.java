package ru.bellski.lang.metasql.psi.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.bellski.lang.metasql.psi.MetaSqlImportReferenceElement;
import ru.bellski.lang.metasql.psi.MetaSqlPsiCompositeElement;
import ru.bellski.lang.metasql.psi.MetaSqlPsiImportStatementBase;

/**
 * Created by oem on 6/24/16.
 */
public class MetaSqlPsiImportStatementBaseImpl extends MetaSqlPsiCompositeElementImpl implements MetaSqlPsiImportStatementBase {

    public MetaSqlPsiImportStatementBaseImpl(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public MetaSqlImportReferenceElement getImportReferenceElement() {
        return null;
    }
}
