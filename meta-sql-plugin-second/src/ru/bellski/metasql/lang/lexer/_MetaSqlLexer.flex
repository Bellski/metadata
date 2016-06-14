package ru.bellski.metasql.lang.lexer;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import static ru.bellski.metasql.lang.psi.MetaSqlTokenTypes.*;

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

WHITE_SPACE=[ \t\n\x0B\f\r]+
IDENTIFIER=[a-zA-Z_0-9]+
STRING=('([^'\\]|\\.)*'|\"([^\"\\]|\\\"|\\'|\\)*\")
LINE_COMMENT="//".*

%%
<YYINITIAL> {
  {WHITE_SPACE}       { return com.intellij.psi.TokenType.WHITE_SPACE; }

  "{"                 { return LBRACE; }
  "}"                 { return RBRACE; }
  "["                 { return LBRACKET; }
  "]"                 { return RBRACKET; }
  ";"                 { return SEMICOLON; }
  "<"                 { return LT; }
  ">"                 { return RT; }
  "="                 { return EQ; }
  ","                 { return COMMA; }
  "."                 { return DOT; }
  "List"              { return LIST; }
  "Boolean"           { return BOOLEAN; }
  "String"            { return STRING; }
  "Long"              { return LONG; }
  "import"            { return IMPORT_KEYWORD; }
  "sqlMetadata"       { return METADATA_KEYWORD; }
  "parameters"        { return PARAMETERS_KEYWORD; }
  "return"            { return RETURN_KEYWORD; }
  "package"           { return PACKAGE_KEYWORD; }
  "metaQuery"         { return META_QUERY_KEYWORD; }

  {IDENTIFIER}        { return IDENTIFIER; }
  {STRING}            { return STRING; }
  {LINE_COMMENT}      { return LINE_COMMENT; }

  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
