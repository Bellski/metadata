package ru.bellski.lang.metasql;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import ru.bellski.lang.metasql.lexer.MetaSqlLexerAdapter;


/**
 * Created by oem on 5/19/16.
 */
public class MetaSqlParserDefinition implements ParserDefinition{
    private static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    private static final TokenSet COMMENTS = TokenSet.create(MetaSqlTokenTypes.LINE_COMMENT);
    public static final TokenSet LITERALS = TokenSet.create(MetaSqlTokenTypes.LITERAL);

    public static final IFileElementType FILE =
            new IFileElementType(Language.findInstance(MetaSqlLanguage.class));

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new MetaSqlLexerAdapter();
    }

    @Override
    public PsiParser createParser(Project project) {
        return new MetaSqlParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return LITERALS;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return MetaSqlTokenTypes.Factory.createElement(node);
    }

    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new MetaSqlFile(viewProvider);
    }

    @Override
    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }
}
