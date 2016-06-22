package ru.bellski.metasql.lang.style;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by oem on 6/22/16.
 */
public class MetaSqlCodeStyleSettings extends CustomCodeStyleSettings {

    protected MetaSqlCodeStyleSettings(CodeStyleSettings container) {
        super("MetaSqlCodeStyleSettings", container);
    }
}
