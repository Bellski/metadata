// This is a generated file. Not intended for manual editing.
package ru.bellski.lang.metasql;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static ru.bellski.lang.metasql.MetaSqlTokenTypes.*;
import static ru.bellski.lang.metasql.psi.impl.MetaSqlGeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class MetaSqlParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == CODE_REFERENCE_ELEMENT) {
      r = codeReferenceElement(b, 0);
    }
    else if (t == IMPORT_LIST) {
      r = importList(b, 0);
    }
    else if (t == IMPORT_REFERENCE_ELEMENT) {
      r = importReferenceElement(b, 0);
    }
    else if (t == IMPORT_STATEMENT) {
      r = importStatement(b, 0);
    }
    else if (t == META_QUERY) {
      r = metaQuery(b, 0);
    }
    else if (t == METADATA_ASSIGN) {
      r = metadataAssign(b, 0);
    }
    else if (t == PACKAGE_REFERENCE_ELEMENT) {
      r = packageReferenceElement(b, 0);
    }
    else if (t == PACKAGE_STATEMENT) {
      r = packageStatement(b, 0);
    }
    else if (t == PARAMETER_VARIABLE) {
      r = parameterVariable(b, 0);
    }
    else if (t == PARAMETERS_ASSIGN) {
      r = parametersAssign(b, 0);
    }
    else if (t == REFERENCE_PARAMETER_LIST) {
      r = referenceParameterList(b, 0);
    }
    else if (t == RETURN_STATEMENT) {
      r = returnStatement(b, 0);
    }
    else if (t == TYPE_ELEMENT) {
      r = typeElement(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return metaSqlFile(b, l + 1);
  }

  /* ********************************************************** */
  // IDENTIFIER referenceParameterList
  public static boolean codeReferenceElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "codeReferenceElement")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && referenceParameterList(b, l + 1);
    exit_section_(b, m, CODE_REFERENCE_ELEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // importStatement*
  public static boolean importList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importList")) return false;
    Marker m = enter_section_(b, l, _NONE_, IMPORT_LIST, "<import list>");
    int c = current_position_(b);
    while (true) {
      if (!importStatement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "importList", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER (('.' IDENTIFIER)*)?
  public static boolean importReferenceElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importReferenceElement")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && importReferenceElement_1(b, l + 1);
    exit_section_(b, m, IMPORT_REFERENCE_ELEMENT, r);
    return r;
  }

  // (('.' IDENTIFIER)*)?
  private static boolean importReferenceElement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importReferenceElement_1")) return false;
    importReferenceElement_1_0(b, l + 1);
    return true;
  }

  // ('.' IDENTIFIER)*
  private static boolean importReferenceElement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importReferenceElement_1_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!importReferenceElement_1_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "importReferenceElement_1_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // '.' IDENTIFIER
  private static boolean importReferenceElement_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importReferenceElement_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // 'import' importReferenceElement ';'
  public static boolean importStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importStatement")) return false;
    if (!nextTokenIs(b, IMPORT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IMPORT);
    r = r && importReferenceElement(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, IMPORT_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // 'metaQuery' IDENTIFIER? '{' (metadataAssign? parametersAssign? returnStatement?)'}'
  public static boolean metaQuery(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metaQuery")) return false;
    if (!nextTokenIs(b, METAQUERY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, META_QUERY, null);
    r = consumeToken(b, METAQUERY);
    p = r; // pin = 1
    r = r && report_error_(b, metaQuery_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, LBRACE)) && r;
    r = p && report_error_(b, metaQuery_3(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // IDENTIFIER?
  private static boolean metaQuery_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metaQuery_1")) return false;
    consumeToken(b, IDENTIFIER);
    return true;
  }

  // metadataAssign? parametersAssign? returnStatement?
  private static boolean metaQuery_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metaQuery_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = metaQuery_3_0(b, l + 1);
    r = r && metaQuery_3_1(b, l + 1);
    r = r && metaQuery_3_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // metadataAssign?
  private static boolean metaQuery_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metaQuery_3_0")) return false;
    metadataAssign(b, l + 1);
    return true;
  }

  // parametersAssign?
  private static boolean metaQuery_3_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metaQuery_3_1")) return false;
    parametersAssign(b, l + 1);
    return true;
  }

  // returnStatement?
  private static boolean metaQuery_3_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metaQuery_3_2")) return false;
    returnStatement(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // packageStatement? importList metaQuery?
  static boolean metaSqlFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metaSqlFile")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = metaSqlFile_0(b, l + 1);
    r = r && importList(b, l + 1);
    r = r && metaSqlFile_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // packageStatement?
  private static boolean metaSqlFile_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metaSqlFile_0")) return false;
    packageStatement(b, l + 1);
    return true;
  }

  // metaQuery?
  private static boolean metaSqlFile_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metaSqlFile_2")) return false;
    metaQuery(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // 'sqlMetadata' '=' typeElement ';'
  public static boolean metadataAssign(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metadataAssign")) return false;
    if (!nextTokenIs(b, SQLMETADATA)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SQLMETADATA);
    r = r && consumeToken(b, EQ);
    r = r && typeElement(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, METADATA_ASSIGN, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER (('.' IDENTIFIER)*)?
  public static boolean packageReferenceElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "packageReferenceElement")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && packageReferenceElement_1(b, l + 1);
    exit_section_(b, m, PACKAGE_REFERENCE_ELEMENT, r);
    return r;
  }

  // (('.' IDENTIFIER)*)?
  private static boolean packageReferenceElement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "packageReferenceElement_1")) return false;
    packageReferenceElement_1_0(b, l + 1);
    return true;
  }

  // ('.' IDENTIFIER)*
  private static boolean packageReferenceElement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "packageReferenceElement_1_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!packageReferenceElement_1_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "packageReferenceElement_1_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // '.' IDENTIFIER
  private static boolean packageReferenceElement_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "packageReferenceElement_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // 'package' packageReferenceElement ';'
  public static boolean packageStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "packageStatement")) return false;
    if (!nextTokenIs(b, PACKAGE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PACKAGE);
    r = r && packageReferenceElement(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, PACKAGE_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // typeElement IDENTIFIER
  public static boolean parameterVariable(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterVariable")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = typeElement(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, m, PARAMETER_VARIABLE, r);
    return r;
  }

  /* ********************************************************** */
  // 'parameters' '=' '[' (parameterVariable ((',' parameterVariable)*)?) ']' ';'
  public static boolean parametersAssign(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parametersAssign")) return false;
    if (!nextTokenIs(b, PARAMETERS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PARAMETERS);
    r = r && consumeToken(b, EQ);
    r = r && consumeToken(b, LBRACKET);
    r = r && parametersAssign_3(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, PARAMETERS_ASSIGN, r);
    return r;
  }

  // parameterVariable ((',' parameterVariable)*)?
  private static boolean parametersAssign_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parametersAssign_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parameterVariable(b, l + 1);
    r = r && parametersAssign_3_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((',' parameterVariable)*)?
  private static boolean parametersAssign_3_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parametersAssign_3_1")) return false;
    parametersAssign_3_1_0(b, l + 1);
    return true;
  }

  // (',' parameterVariable)*
  private static boolean parametersAssign_3_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parametersAssign_3_1_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!parametersAssign_3_1_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parametersAssign_3_1_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ',' parameterVariable
  private static boolean parametersAssign_3_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parametersAssign_3_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && parameterVariable(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ('<' typeElement '>')?
  public static boolean referenceParameterList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "referenceParameterList")) return false;
    Marker m = enter_section_(b, l, _NONE_, REFERENCE_PARAMETER_LIST, "<reference parameter list>");
    referenceParameterList_0(b, l + 1);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // '<' typeElement '>'
  private static boolean referenceParameterList_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "referenceParameterList_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LT);
    r = r && typeElement(b, l + 1);
    r = r && consumeToken(b, GT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // 'return' typeElement ';'
  public static boolean returnStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnStatement")) return false;
    if (!nextTokenIs(b, RETURN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RETURN);
    r = r && typeElement(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, RETURN_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // codeReferenceElement
  public static boolean typeElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeElement")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = codeReferenceElement(b, l + 1);
    exit_section_(b, m, TYPE_ELEMENT, r);
    return r;
  }

}
