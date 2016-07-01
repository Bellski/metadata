// This is a generated file. Not intended for manual editing.
package ru.bellski.lang.metasql;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import ru.bellski.lang.metasql.psi.impl.*;

public interface MetaSqlTokenTypes {

  IElementType CODE_REFERENCE_ELEMENT = new MetaSqlElementType("CODE_REFERENCE_ELEMENT");
  IElementType IMPORT_LIST = new MetaSqlElementType("IMPORT_LIST");
  IElementType IMPORT_REFERENCE_ELEMENT = new MetaSqlElementType("IMPORT_REFERENCE_ELEMENT");
  IElementType IMPORT_STATEMENT = new MetaSqlElementType("IMPORT_STATEMENT");
  IElementType METADATA_ASSIGN = new MetaSqlElementType("METADATA_ASSIGN");
  IElementType META_QUERY = new MetaSqlElementType("META_QUERY");
  IElementType PACKAGE_REFERENCE_ELEMENT = new MetaSqlElementType("PACKAGE_REFERENCE_ELEMENT");
  IElementType PACKAGE_STATEMENT = new MetaSqlElementType("PACKAGE_STATEMENT");
  IElementType PARAMETERS_ASSIGN = new MetaSqlElementType("PARAMETERS_ASSIGN");
  IElementType PARAMETER_VARIABLE = new MetaSqlElementType("PARAMETER_VARIABLE");
  IElementType REFERENCE_PARAMETER_LIST = new MetaSqlElementType("REFERENCE_PARAMETER_LIST");
  IElementType RETURN_STATEMENT = new MetaSqlElementType("RETURN_STATEMENT");
  IElementType TYPE_ELEMENT = new MetaSqlElementType("TYPE_ELEMENT");

  IElementType COLON = new MetaSqlElementType(":");
  IElementType COMMA = new MetaSqlElementType(",");
  IElementType DOT = new MetaSqlElementType(".");
  IElementType EQ = new MetaSqlElementType("=");
  IElementType GT = new MetaSqlElementType(">");
  IElementType IDENTIFIER = new MetaSqlElementType("IDENTIFIER");
  IElementType IMPORT = new MetaSqlElementType("import");
  IElementType LBRACE = new MetaSqlElementType("{");
  IElementType LBRACKET = new MetaSqlElementType("[");
  IElementType LINE_COMMENT = new MetaSqlElementType("LINE_COMMENT");
  IElementType LITERAL = new MetaSqlElementType("LITERAL");
  IElementType LT = new MetaSqlElementType("<");
  IElementType METAQUERY = new MetaSqlElementType("metaQuery");
  IElementType PACKAGE = new MetaSqlElementType("package");
  IElementType PARAMETERS = new MetaSqlElementType("parameters");
  IElementType RBRACE = new MetaSqlElementType("}");
  IElementType RBRACKET = new MetaSqlElementType("]");
  IElementType RETURN = new MetaSqlElementType("return");
  IElementType SEMICOLON = new MetaSqlElementType(";");
  IElementType SQLMETADATA = new MetaSqlElementType("sqlMetadata");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == CODE_REFERENCE_ELEMENT) {
        return new MetaSqlCodeReferenceElementImpl(node);
      }
      else if (type == IMPORT_LIST) {
        return new MetaSqlImportListImpl(node);
      }
      else if (type == IMPORT_REFERENCE_ELEMENT) {
        return new MetaSqlImportReferenceElementImpl(node);
      }
      else if (type == IMPORT_STATEMENT) {
        return new MetaSqlImportStatementImpl(node);
      }
      else if (type == METADATA_ASSIGN) {
        return new MetaSqlMetadataAssignImpl(node);
      }
      else if (type == META_QUERY) {
        return new MetaSqlMetaQueryImpl(node);
      }
      else if (type == PACKAGE_REFERENCE_ELEMENT) {
        return new MetaSqlPackageReferenceElementImpl(node);
      }
      else if (type == PACKAGE_STATEMENT) {
        return new MetaSqlPackageStatementImpl(node);
      }
      else if (type == PARAMETERS_ASSIGN) {
        return new MetaSqlParametersAssignImpl(node);
      }
      else if (type == PARAMETER_VARIABLE) {
        return new MetaSqlParameterVariableImpl(node);
      }
      else if (type == REFERENCE_PARAMETER_LIST) {
        return new MetaSqlReferenceParameterListImpl(node);
      }
      else if (type == RETURN_STATEMENT) {
        return new MetaSqlReturnStatementImpl(node);
      }
      else if (type == TYPE_ELEMENT) {
        return new MetaSqlTypeElementImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
