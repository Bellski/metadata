package ru.bellski.metasql.lang.psi;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bellski.metasql.lang.MetaSqlLanguage;

/**
 * Created by oem on 5/19/16.
 */
public class MetaSqlTokenType extends IElementType {
    public MetaSqlTokenType(@NotNull @NonNls String debugName) {
        super(debugName, MetaSqlLanguage.INSTANCE);
    }
}
