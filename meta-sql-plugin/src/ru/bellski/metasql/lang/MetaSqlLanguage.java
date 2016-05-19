package ru.bellski.metasql.lang;

import com.intellij.lang.Language;

/**
 * Created by oem on 5/19/16.
 */
public class MetaSqlLanguage extends Language {
    public static final MetaSqlLanguage INSTANCE = new MetaSqlLanguage();

    private MetaSqlLanguage() {
        super("MetaSql");
    }
}
