package ru.bellski.metasql.contributor;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.lang.MetaSqlFile;
import ru.bellski.metasql.lang.psi.MetaSqlMetadataTypeExpression;
import ru.bellski.metasql.lang.psi.MetaSqlTypeReference;

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
                        .withSuperParent(1, MetaSqlTypeReference.class),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
                        System.out.println(parameters.getPosition().getClass());
                    }
                }
        );

        extend(
                CompletionType.BASIC,
                PlatformPatterns
                        .psiElement()
                        .andNot(PlatformPatterns
                                .psiElement()
                                .withSuperParent(1, MetaSqlTypeReference.class)),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
                        result.addAllElements(Arrays.asList(LookupElementBuilder.create("Metadata = "), LookupElementBuilder.create("ReturnType = ")));
                    }
                }
        );
    }
}
