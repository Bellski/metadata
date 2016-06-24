package ru.bellski.lang.metasql;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by oem on 6/24/16.
 */
public class MetaSqlElementType extends IElementType {
    public MetaSqlElementType(@NotNull @NonNls String debugName) {
        super(debugName, MetaSqlLanguage.INSTANCE);
    }
}
