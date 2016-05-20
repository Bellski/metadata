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

LETTER=[a-z]|[A-Z]
IDENTIFIER={LETTER}*

%%
<YYINITIAL> {
  {WHITE_SPACE}      { return com.intellij.psi.TokenType.WHITE_SPACE; }

  "Metadata"         { return METADATA; }
  "ReturnType"       { return RETURN_TYPE; }
  {IDENTIFIER}       { return IDENTIFIER; }


  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
