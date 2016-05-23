package ru.bellski.metasql.lang.lexer;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import static ru.bellski.metasql.lang.psi.MetaSqlTypes.*;

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

IDENTIFIER=([a-zA-Z_$][a-zA-Z\d_$]*\.)*[a-zA-Z_$][a-zA-Z\d_$]*

%%
<YYINITIAL> {
  {WHITE_SPACE}      { return com.intellij.psi.TokenType.WHITE_SPACE; }

  ";"                { return SEMI; }
  "="                { return EQ; }
  "Metadata"         { return METADATA; }
  "ReturnRule"       { return RETURNRULE; }
  "List"             { return LIST; }
  "Boolean"          { return BOOLEAN; }
  "Integer"          { return INTEGER; }
  "Single"           { return SINGLE; }
  {IDENTIFIER}       { return IDENTIFIER; }


  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
