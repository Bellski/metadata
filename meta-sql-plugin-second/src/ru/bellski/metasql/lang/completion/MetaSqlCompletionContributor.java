package ru.bellski.metasql.lang.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.lang.MetaSqlFile;
import ru.bellski.metasql.lang.parser.MetaSqlCompletionUtil;
import ru.bellski.metasql.lang.psi.MetaSqlQueryMembers;

/**
 * Created by oem on 6/8/16.
 */
public class MetaSqlCompletionContributor extends CompletionContributor {
    public MetaSqlCompletionContributor() {


        extend(
                CompletionType.BASIC,
                PlatformPatterns
                        .psiElement()
                        .withSuperParent(2, MetaSqlQueryMembers.class),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters,
                                                  ProcessingContext context,
                                                  @NotNull CompletionResultSet result) {


                        result.addAllElements(MetaSqlCompletionUtil.buildMembersCompletion((MetaSqlFile) parameters.getOriginalFile()));
                    }
                }
        );
    }
}
