package ru.bellski.metasql.lang.parser;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.util.PsiTreeUtil;
import ru.bellski.metasql.lang.MetaSqlFile;
import ru.bellski.metasql.lang.psi.MetaSqlMetadataDefinition;
import ru.bellski.metasql.lang.psi.MetaSqlParameterDefinition;
import ru.bellski.metasql.lang.psi.MetaSqlTokenTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oem on 6/8/16.
 */
public class MetaSqlCompletionUtil {

    public static List<LookupElement> buildMembersCompletion(MetaSqlFile metaSqlFile) {
        final List<LookupElement> elements = new ArrayList<>();

        if (hasMetadataDefinition(metaSqlFile) && hasParametersDefinition(metaSqlFile)) {

        } else
            if (hasMetadataDefinition(metaSqlFile)) {
                elements.add(LookupElementBuilder.create(MetaSqlTokenTypes.PARAMETERS_KEYWORD));
            } else
                if (hasParametersDefinition(metaSqlFile)) {
                    elements.add(LookupElementBuilder.create(MetaSqlTokenTypes.METADATA_KEYWORD));
                } else {
                    elements.add(LookupElementBuilder.create(MetaSqlTokenTypes.METADATA_KEYWORD));
                    elements.add(LookupElementBuilder.create(MetaSqlTokenTypes.PARAMETERS_KEYWORD));
                }

        return elements;
    }

    public static boolean hasMetadataDefinition(MetaSqlFile metaSqlFile) {
        return PsiTreeUtil.findChildOfType(metaSqlFile, MetaSqlMetadataDefinition.class) != null;
    }

    public static boolean hasParametersDefinition(MetaSqlFile metaSqlFile) {
        return PsiTreeUtil.findChildOfType(metaSqlFile, MetaSqlParameterDefinition.class) != null;
    }
}
