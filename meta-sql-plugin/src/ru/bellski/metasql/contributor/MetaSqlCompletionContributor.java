package ru.bellski.metasql.contributor;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiClass;
import com.intellij.psi.TokenType;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.SqlMetadataJavaClassCache;
import ru.bellski.metasql.lang.psi.*;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Created by oem on 5/19/16.
 */
public class MetaSqlCompletionContributor extends CompletionContributor {

    public MetaSqlCompletionContributor() {

        extend(
                CompletionType.BASIC,
                PlatformPatterns
                        .psiElement()
                        .withSuperParent(2, MetaSqlReturnRuleClause.class)
                        .afterLeaf(PlatformPatterns.psiElement(MetaSqlTypes.EQ)),
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
                        .psiElement()
                        .withSuperParent(2, MetaSqlBody.class),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
                        result.addAllElements(Arrays.asList(LookupElementBuilder.create("Metadata = "), LookupElementBuilder.create("ReturnRule = ")));
                    }
                }
        );

        extend(
                CompletionType.BASIC,
                PlatformPatterns
                        .psiElement()
                        .afterSiblingSkipping(PlatformPatterns.psiElement(TokenType.WHITE_SPACE), PlatformPatterns.psiElement(ReturnRuleClause.class)),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
                        SqlMetadataJavaClassCache
                                .getInstance(parameters.getPosition().getProject())
                                .getAllSqlMetaClasses()
                                .forEach(psiClass -> result.addElement(new JavaPsiClassReferenceElement(psiClass)));
                    }
                }
        );
    }

}
