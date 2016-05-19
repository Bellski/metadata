package ru.bellski.metasql.contributor;

import com.intellij.codeInsight.completion.*;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.lang.psi.MetaSqlId;
import ru.bellski.metasql.lang.psi.MetaSqlMetadataBody;
import ru.bellski.metasql.lang.psi.MetaSqlMetadataElement;
import ru.bellski.metasql.lang.psi.MetaSqlMetadataIdentifier;

/**
 * Created by oem on 5/19/16.
 */
public class MetaSqlCompletionContributor extends CompletionContributor {
    public MetaSqlCompletionContributor() {
        extend(
                CompletionType.BASIC,
                PlatformPatterns
                        .psiElement()
                        .withSuperParent(1, MetaSqlId.class),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
                        System.out.println(parameters.getPosition().getClass());
                    }
                }
        );
    }
}
