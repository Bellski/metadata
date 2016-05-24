package ru.bellski.metasql.lang.lexer;

import com.intellij.lexer.FlexAdapter;

/**
 * Created by oem on 5/19/16.
 */
public class MetaSqlLexeraAdapter extends FlexAdapter {
    public MetaSqlLexeraAdapter() {
        super(new _MetaSqlLexer(null));
    }
}
