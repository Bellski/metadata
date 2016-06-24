package ru.bellski.metasql.lang.style;

import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.application.options.SmartIndentOptionsEditor;
import com.intellij.ide.JavaLanguageCodeStyleSettingsProvider;
import com.intellij.json.JsonLanguage;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

    }

    @Nullable
    @Override
    public IndentOptionsEditor getIndentOptionsEditor() {
        return new SmartIndentOptionsEditor();
    }

    @Nullable
    @Override
    public CommonCodeStyleSettings getDefaultCommonSettings() {
        CommonCodeStyleSettings commonSettings = new CommonCodeStyleSettings(JsonLanguage.INSTANCE);
        CommonCodeStyleSettings.IndentOptions indentOptions = commonSettings.initIndentOptions();
        indentOptions.INDENT_SIZE = 2;
        // strip all blank lines by default
        commonSettings.KEEP_BLANK_LINES_IN_CODE = 0;
        return commonSettings;
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
