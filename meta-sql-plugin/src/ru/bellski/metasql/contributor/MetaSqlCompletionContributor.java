package ru.bellski.metasql.contributor;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.lang.MetaSqlFile;
import ru.bellski.metasql.lang.psi.MetaSqlMetadataClause;
import ru.bellski.metasql.lang.psi.MetaSqlReturnRuleClause;
import ru.bellski.metasql.lang.psi.ReturnRuleClause;

import java.util.Arrays;

/**
 * Created by oem on 5/19/16.
 */
public class MetaSqlCompletionContributor extends CompletionContributor {

    public MetaSqlCompletionContributor() {

        extend(
                CompletionType.BASIC,
                PlatformPatterns
                        .psiElement()
                        .withSuperParent(1, MetaSqlReturnRuleClause.class),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
                        result.addAllElements(
                                Arrays.asList(
                                        LookupElementBuilder.create("List"),
                                        LookupElementBuilder.create("Boolean"),
                                        LookupElementBuilder.create("Integer"),
                                        LookupElementBuilder.create("Single")
                                )
                        );
                    }
                }
        );

        extend(
                CompletionType.BASIC,
                PlatformPatterns
                        .psiElement(),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
                        result.addAllElements(Arrays.asList(LookupElementBuilder.create("Metadata = "), LookupElementBuilder.create("ReturnType = ")));
                    }
                }
        );
    }

}
