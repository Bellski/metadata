package ru.bellski.metasql;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.sql.psi.SqlFile;
import com.intellij.sql.psi.SqlLanguage;
import com.intellij.sql.psi.impl.SqlStringTokenElement;
import com.intellij.util.Consumer;
import com.intellij.util.ProcessingContext;
import com.intellij.util.containers.HashSet;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.stream.Collectors;

public class CustomModelCompletionContributor extends CompletionContributor {
    public CustomModelCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement().withLanguage(SqlLanguage.getInstance()), new CompletionProvider<CompletionParameters>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
                final MetaSqlFilesCache metaSqlFilesCache = MetaSqlFilesCache.getInstance(parameters.getPosition().getProject());
                final boolean isMetadataComment =
                        metaSqlFilesCache
                                .findMetaSqlFile((SqlFile) parameters.getOriginalFile())
                                .commentWithMetadataEquals(parameters.getPosition());

                if (isMetadataComment) {
                    result.addAllElements(
                            SqlMetadataJavaClassCache
                                    .getInstance(parameters.getPosition().getProject())
                                    .getAllSqlMetaClasses()
                                    .stream()
                                    .map(JavaPsiClassReferenceElement::new)
                                    .collect(Collectors.toList())
                    );
                } else if (parameters.getPosition() instanceof SqlStringTokenElement) {
                    final MetaSqlFile metaSqlFile = metaSqlFilesCache.findMetaSqlFile((SqlFile) parameters.getOriginalFile());

                    if (metaSqlFile != null && metaSqlFile.hasMetadataClass()) {
                        result.addAllElements(
                                metaSqlFilesCache
                                        .findMetaSqlFile((SqlFile) parameters.getOriginalFile())
                                        .getMetaValues()
                                        .stream()
                                        .map(LookupElementBuilder::create)
                                        .collect(Collectors.toList())
                        );
                    }
                }
            }
        });
    }
}

