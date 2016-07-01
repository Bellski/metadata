package ru.bellski.lang.metasql.generator;

import com.intellij.psi.*;
import ru.bellski.lang.metasql.psi.MetaSqlCodeReferenceElement;
import ru.bellski.lang.metasql.psi.MetaSqlTypeElement;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by oem on 6/29/16.
 */
public class ResultSetRules {

    private static Map<String, String> literalTypes = new HashMap<>();

    static {
        literalTypes.put(
                "String",
                "resultSet.getString(0)"
        );

        literalTypes.put(
                "Boolean",
                "resultSet.getBoolean(0)"
        );

        literalTypes.put(
                "Long",
                "resultSet.getLong(0)"
        );
    }

    public static String getGeneratedResultForType(PsiTypeElement type, MetaSqlCodeReferenceElement returnType) {
        String result = null;

        if (type != null) {
            if (literalTypes.containsKey(type.getText())) {
                result = "if (resultSet.next()) {"
                        + literalTypes.get(type.getText()) + ";"
                        + "}"
                ;
            }

            if (returnType != null) {
                final MetaSqlTypeElement parameterType = returnType.getReferenceParameterList().getTypeElement();

                if (parameterType == null) {
                    final PsiClass resolvedReturnType = (PsiClass) returnType.resolve();

                    for (PsiAnnotation psiAnnotation : resolvedReturnType.getModifierList().getAnnotations()) {
                        if ("ru.bellski.metadata.SqlMetadata".equals(psiAnnotation.getQualifiedName())) {
                            result = "result = " + returnType.getText() + ".unmarshal(resultSet);";
                        }
                    }
                }
            }

            if (result == null) {
                final PsiJavaCodeReferenceElement codeReference = type.getInnermostComponentReferenceElement();

                if (codeReference != null) {
                    final PsiReferenceParameterList parameterList = codeReference.getParameterList();

                    if (parameterList != null) {
                        final PsiTypeElement[] parameterElements = parameterList.getTypeParameterElements();

                        if (parameterElements.length == 1) {
                            result = "if (resultSet.next()) {"
                                    + "    result = new ArrayList<>();"
                                    + "    result.add(" + literalTypes.get(parameterElements[0].getText()) + ");"
                                    + "    while (resultSet.next()) {"
                                    + "        result.add(" + literalTypes.get(parameterElements[0].getText()) + ");"
                                    + "    }"
                                    + "}"
                            ;
                        }
                    }
                }
            }
        }

        return result == null ? "" : result;
    }

    public static String generateReturnResult(PsiType returnType) {
        return returnType.getPresentableText().equals("void") ? "" : "return result;";
    }


}
