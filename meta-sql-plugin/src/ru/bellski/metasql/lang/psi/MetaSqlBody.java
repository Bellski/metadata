package ru.bellski.metasql.lang.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by oem on 5/23/16.
 */
public interface MetaSqlBody extends MetaSqlCompositeElement {
    @Nullable
    MetadataClause getMetadataClause();

    @NotNull
    ReturnRuleClause getReturnRuleClause();
}
