package ru.bellski.metasql.lang.style;

import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.lang.MetaSqlLanguage;

/**
 * Created by oem on 6/22/16.
 */
public class MetaSqlLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {

    @NotNull
    @Override
    public Language getLanguage() {
        return MetaSqlLanguage.INSTANCE;
    }

    @Override
    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
        if (settingsType == SettingsType.SPACING_SETTINGS) {
            consumer.showStandardOptions("SPACE_AROUND_ASSIGNMENT_OPERATORS");
            consumer.renameStandardOption("SPACE_AROUND_ASSIGNMENT_OPERATORS", "Separator");
        } else if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {
            consumer.showStandardOptions("BLANK_LINES_AFTER_IMPORTS");
            consumer.renameStandardOption("BLANK_LINES_AFTER_IMPORTS", "ImportList");
        }
    }

    @Override
    public String getCodeSample(@NotNull SettingsType settingsType) {
        return "import aaa.aa;\n" +
                "\n" +
                "metaQuery aaa {\n" +
                "\n" +
                "}";
    }
}
