package ru.bellski.lang.metasql.lexer;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import static ru.bellski.lang.metasql.MetaSqlTokenTypes.*;

%%

%{
  public _MetaSqlLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _MetaSqlLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL="\r"|"\n"|"\r\n"
LINE_WS=[\ \t\f]
WHITE_SPACE=({LINE_WS}|{EOL})+

SPACE=[ \t\n\x0B\f\r]+
LINE_COMMENT="//".*
IDENTIFIER=[:letter:][a-zA-Z_0-9]*
LITERAL=('([^'\\]|\\.)*'|\"([^\"\\]|\\.)*\")

%%
<YYINITIAL> {
  {WHITE_SPACE}       { return com.intellij.psi.TokenType.WHITE_SPACE; }

  "."                 { return DOT; }
  ";"                 { return SEMICOLON; }
  ":"                 { return COLON; }
  "{"                 { return LBRACE; }
  "}"                 { return RBRACE; }
  "["                 { return LBRACKET; }
  "]"                 { return RBRACKET; }
  "<"                 { return LT; }
  ">"                 { return GT; }
  "="                 { return EQ; }
  ","                 { return COMMA; }
  "import"            { return IMPORT; }
  "metaQuery"         { return METAQUERY; }
  "sqlMetadata"       { return SQLMETADATA; }
  "parameters"        { return PARAMETERS; }
  "return"            { return RETURN; }
  "package"           { return PACKAGE; }

  {LINE_COMMENT}      { return LINE_COMMENT; }
  {IDENTIFIER}        { return IDENTIFIER; }
  {LITERAL}           { return LITERAL; }

  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
