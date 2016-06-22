package ru.bellski.metasql.lang.style;

import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.openapi.options.Configurable;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bellski.metasql.lang.MetaSqlFile;
import ru.bellski.metasql.lang.MetaSqlLanguage;

/**
 * Created by oem on 6/22/16.
 */
public class MetaSqlCodeStyleSettingsProvider extends CodeStyleSettingsProvider {

    @Nullable
    @Override
    public CustomCodeStyleSettings createCustomSettings(CodeStyleSettings settings) {
        return new MetaSqlCodeStyleSettings(settings);
    }

    @Nullable
    @Override
    public String getConfigurableDisplayName() {
        return "MetaSql";
    }

    @NotNull
    @Override
    public Configurable createSettingsPage(CodeStyleSettings settings, CodeStyleSettings originalSettings) {
        return new CodeStyleAbstractConfigurable(settings, originalSettings, "MetaSql") {

            @Nullable
            @Override
            public String getHelpTopic() {
                return null;
            }

            @Override
            protected CodeStyleAbstractPanel createPanel(CodeStyleSettings settings) {
                return new MetaSqlCodeStyleMainPanel(getCurrentSettings(), settings);
            }
        };
    }

    private static class MetaSqlCodeStyleMainPanel extends TabbedLanguageCodeStylePanel {
        public MetaSqlCodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
            super(MetaSqlLanguage.INSTANCE, currentSettings, settings);
        }
    }
}
