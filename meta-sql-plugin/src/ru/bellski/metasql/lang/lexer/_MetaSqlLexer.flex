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

mLETTER = [:letter:] | "_"
mDIGIT = [:digit:]

PARAM_KEYWORD={mLETTER} ({mDIGIT} | {mLETTER})*


%%
<YYINITIAL> {
  {WHITE_SPACE}       { return com.intellij.psi.TokenType.WHITE_SPACE; }

  "keyword"           { return KEYWORD; }
  ";"                 { return SEMI; }
  "="                 { return EQ; }
  "["                 { return LBRACKET; }
  "]"                 { return RBRACKET; }
  ","                 { return COMMA; }
  "Metadata"          { return METADATA; }
  "ReturnRule"        { return RETURNRULE; }
  "Parameters"        { return PARAMETERS; }
  "List"              { return LIST; }
  "Boolean"           { return BOOLEAN; }
  "Integer"           { return INTEGER; }
  "Single"            { return SINGLE; }
  "String"            { return STRING; }
  "Long"              { return LONG; }
  "Date"              { return DATE; }
   {PARAM_KEYWORD}     { return PARAM_KEYWORD; }
  {IDENTIFIER}      { return M_IDENTIFIER; }


  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
