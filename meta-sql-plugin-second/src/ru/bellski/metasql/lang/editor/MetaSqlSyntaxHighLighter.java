package ru.bellski.metasql.lang.editor;

import com.intellij.ide.highlighter.JavaHighlightingColors;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.HashMap;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.lang.lexer.MetaSqlLexeraAdapter;

import static ru.bellski.metasql.lang.psi.MetaSqlTokenTypes.*;


import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

/**
 * Created by oem on 6/7/16.
 */
public class MetaSqlSyntaxHighLighter extends SyntaxHighlighterBase {

    private static final Map<IElementType, TextAttributesKey> textAttributeByElementType = new HashMap<>();

    static {
        textAttributeByElementType.put(TokenType.BAD_CHARACTER, createTextAttributesKey("META_SQL_ILLEGAL", DefaultLanguageHighlighterColors.INVALID_STRING_ESCAPE));

        textAttributeByElementType.put(META_QUERY_KEYWORD, createTextAttributesKey("META_SQL_QUERY_KEYWORD", JavaHighlightingColors.KEYWORD));

        textAttributeByElementType.put(PARAMETERS_KEYWORD, createTextAttributesKey("META_SQL_PARAMETERS_KEYWORD", DefaultLanguageHighlighterColors.STATIC_FIELD));

        textAttributeByElementType.put(METADATA_KEYWORD, createTextAttributesKey("META_SQL_METADATA_KEYWORD", DefaultLanguageHighlighterColors.STATIC_FIELD));

        textAttributeByElementType.put(COMMA, createTextAttributesKey("META_SQL_COMMA", JavaHighlightingColors.COMMA));

        textAttributeByElementType.put(SEMICOLON, createTextAttributesKey("META_SQL_SEMICOLON", JavaHighlightingColors.JAVA_SEMICOLON));

        textAttributeByElementType.put(IMPORT_KEYWORD, createTextAttributesKey("META_SQL_IMPORT_KEYWORD", JavaHighlightingColors.KEYWORD));

        textAttributeByElementType.put(PACKAGE_KEYWORD, createTextAttributesKey("META_SQL_PACKAGE_KEYWORD", JavaHighlightingColors.KEYWORD));

        textAttributeByElementType.put(PACKAGE_KEYWORD, createTextAttributesKey("META_SQL_PACKAGE_KEYWORD", JavaHighlightingColors.KEYWORD));

        textAttributeByElementType.put(RETURN_KEYWORD, createTextAttributesKey("META_SQL_RETURN_KEYWORD", JavaHighlightingColors.KEYWORD));
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new MetaSqlLexeraAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(textAttributeByElementType.get(tokenType));
    }

}
