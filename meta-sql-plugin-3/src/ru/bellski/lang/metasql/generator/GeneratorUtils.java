package ru.bellski.lang.metasql.generator;

import com.intellij.psi.*;
import ru.bellski.lang.metasql.psi.MetaSqlCodeReferenceElement;
import ru.bellski.lang.metasql.psi.MetaSqlParameterVariable;

import java.util.List;
import java.util.StringJoiner;

/**
 * Created by oem on 6/29/16.
 */
public class GeneratorUtils {
    public static String prepareStatementSetParameters(List<MetaSqlParameterVariable> parameters) {
        final StringJoiner stringJoiner = new StringJoiner("\n");

        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) {
                stringJoiner.add("stmp.setObject("+ (i+1) + ", params[" + i + "]);");
            }
        }

        return stringJoiner.toString();
    }

    public static String returnResult(MetaSqlCodeReferenceElement returnType, PsiImportList importList, PsiElementFactory elementFactory) {
        String result = null;

        if (returnType != null) {
            final PsiClass resolvedReturnType = (PsiClass) returnType.resolve();

            for (PsiAnnotation psiAnnotation : resolvedReturnType.getModifierList().getAnnotations()) {
                if ("ru.bellski.metadata.SqlMetadata".equals(psiAnnotation.getQualifiedName())) {
                    final PsiMethod unmarshalMethod = resolvedReturnType.findMethodsByName("unmarshal", false)[0];


                    result = unmarshalMethod.getReturnType().getPresentableText() + " result = null;";
                    importList.add(elementFactory.createImportStatement((PsiClass) unmarshalMethod.getReturnTypeElement().getInnermostComponentReferenceElement().resolve()));
                }
            }

            if (result == null) {
                result = returnType.getText() + " result = null;";
            }
        }

        return result == null ? "" : result;
    }

    public static boolean returnTypeIsMetadata(MetaSqlCodeReferenceElement returnType) {
        boolean returnTypeIsMetadata = false;

        if (returnType != null) {
            PsiClass resolvedReturnType = (PsiClass) returnType.resolve();
            for (PsiAnnotation psiAnnotation : resolvedReturnType.getModifierList().getAnnotations()) {
                if ("ru.bellski.metadata.SqlMetadata".equals(psiAnnotation.getQualifiedName())) {
                    returnTypeIsMetadata = true;
                    break;
                }
            }
        }

        return returnTypeIsMetadata;
    }

    public static PsiClass getMetadataType(PsiClass metadataClass) {
        final PsiMethod unmarshalMethod = metadataClass.findMethodsByName("unmarshal", false)[0];
        return (PsiClass) unmarshalMethod.getReturnTypeElement().getInnermostComponentReferenceElement().resolve();
    }
}
