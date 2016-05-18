package ru.bellski.metasql.contributor;

import com.intellij.codeInsight.completion.*;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.patterns.StandardPatterns;
import com.intellij.psi.PsiComment;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.SqlMetadataJavaClassCache;

import java.util.stream.Collectors;

/**
 * Created by oem on 5/18/16.
 */
public class MetaSqlMetadataCommentCompletionContributor extends CompletionContributor {

    public MetaSqlMetadataCommentCompletionContributor() {
        extend(
                CompletionType.BASIC,
                PlatformPatterns
                        .psiComment()
                        .withText(StandardPatterns.string().startsWith("-- metadata")),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
                        result.addAllElements(
                                SqlMetadataJavaClassCache
                                        .getInstance(parameters.getPosition().getProject())
                                        .getAllSqlMetaClasses()
                                        .stream()
                                        .map(JavaPsiClassReferenceElement::new)
                                        .collect(Collectors.toList())
                        );
                    }
                }
        );
    }
}
