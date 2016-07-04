package ru.bellski.lang.metasql.highlight;

import com.intellij.ide.highlighter.JavaHighlightingColors;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.HashMap;
import org.jetbrains.annotations.NotNull;
import ru.bellski.lang.metasql.MetaSqlTokenTypes;
import ru.bellski.lang.metasql.lexer.MetaSqlLexerAdapter;

import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

/**
 * Created by oem on 6/7/16.
 */
public class MetaSqlSyntaxHighLighter extends SyntaxHighlighterBase {

    private static final Map<IElementType, TextAttributesKey> textAttributeByElementType = new HashMap<>();

    static {
        textAttributeByElementType.put(TokenType.BAD_CHARACTER, createTextAttributesKey("META_SQL_ILLEGAL", DefaultLanguageHighlighterColors.INVALID_STRING_ESCAPE));
        textAttributeByElementType.put(MetaSqlTokenTypes.PACKAGE, createTextAttributesKey("PACKAGE", JavaHighlightingColors.KEYWORD));
        textAttributeByElementType.put(MetaSqlTokenTypes.IMPORT, createTextAttributesKey("IMPORT", JavaHighlightingColors.KEYWORD));
        textAttributeByElementType.put(MetaSqlTokenTypes.METAQUERY, createTextAttributesKey("METAQUERY", JavaHighlightingColors.KEYWORD));
        textAttributeByElementType.put(MetaSqlTokenTypes.RETURN, createTextAttributesKey("RETURN", JavaHighlightingColors.KEYWORD));
        textAttributeByElementType.put(MetaSqlTokenTypes.PARAMETERS, createTextAttributesKey("PARAMETERS", DefaultLanguageHighlighterColors.STATIC_FIELD));
        textAttributeByElementType.put(MetaSqlTokenTypes.SQLMETADATA, createTextAttributesKey("SQLMETADATA", DefaultLanguageHighlighterColors.STATIC_FIELD));
        textAttributeByElementType.put(MetaSqlTokenTypes.COMMA, createTextAttributesKey("COMMA", JavaHighlightingColors.KEYWORD));
        textAttributeByElementType.put(MetaSqlTokenTypes.SEMICOLON, createTextAttributesKey("SEMICOLON", JavaHighlightingColors.KEYWORD));
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new MetaSqlLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(textAttributeByElementType.get(tokenType));
    }


}
