package ru.bellski.metasql.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import ru.bellski.metasql.lang.psi.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by oem on 6/20/16.
 */
public class MetaSqlPsiImplUtil {
    public static String getMetaQueryName(MetaSqlRoot root) {
        return "";
    }

    public static String getPackageName(MetaSqlRoot root) {
        return "";
    }

    public static List<String> getImportNames(MetaSqlRoot root) {
        List<String> names = null;

        final MetaSqlReturnStatement returnStmt = getReturnStatement(root);

        if (returnStmt != null) {
            final MetaSqlReferenceParameter typeWithGeneric = returnStmt.getTypeReference().getReferenceParameter();
            if (typeWithGeneric != null) {
                names = new ArrayList<>();
//                names.add(typeWithGeneric.get.getPackageName());
            }
        }

        return names == null ? Collections.emptyList() : names;
    }

    public static int getParametersCount(MetaSqlRoot root) {
        return PsiTreeUtil.findChildrenOfType(root, MetaSqlParameterDefinition.class).size();
    }

    public static MetaSqlParameterDefinition[] getParameters(MetaSqlRoot root) {
        MetaSqlParameterDefinition[] parameterDefs = null;

        final ASTNode metaQueryDef = root.getNode().findChildByType(MetaSqlTokenTypes.META_QUERY_DEFINITION);

        if (metaQueryDef != null ) {
            ASTNode paramDef = metaQueryDef.findChildByType(MetaSqlTokenTypes.PARAMETERS_DEFINITION);

            if (paramDef != null) {
                final List<MetaSqlParameterDefinition> params = ((MetaSqlParametersDefinition)paramDef.getPsi()).getParameterDefinitionList();
                parameterDefs = params.toArray(new MetaSqlParameterDefinition[params.size()]);
            }
        }

        return parameterDefs == null ? new MetaSqlParameterDefinition[0] : parameterDefs;
    }

    public static MetaSqlReturnStatement getReturnStatement(MetaSqlRoot root) {
        return root.getMetaQueryDefinition().getReturnStatement();
    }

    public static String getTypeName(MetaSqlReferenceElement element) {
        return element.getText();
    }

    public static String getName(MetaSqlCompositeElement element) {
        return element.getNode().findChildByType(MetaSqlTokenTypes.IDENTIFIER).getText();
    }
}
