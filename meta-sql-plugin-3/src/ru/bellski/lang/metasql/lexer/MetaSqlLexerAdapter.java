package ru.bellski.lang.metasql.lexer;

import com.intellij.lexer.FlexAdapter;

/**
 * Created by oem on 5/19/16.
 */
public class MetaSqlLexerAdapter extends FlexAdapter {
    public MetaSqlLexerAdapter() {
        super(new _MetaSqlLexer(null));
    }
}
