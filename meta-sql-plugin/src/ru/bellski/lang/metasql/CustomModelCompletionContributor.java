package ru.bellski.lang.metasql;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.sql.psi.SqlLanguage;
import com.intellij.sql.psi.impl.SqlStringTokenElement;
import com.intellij.util.Consumer;
import com.intellij.util.ProcessingContext;
import com.intellij.util.containers.HashSet;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class CustomModelCompletionContributor extends CompletionContributor {
    public CustomModelCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement().withLanguage(SqlLanguage.getInstance()), new CompletionProvider<CompletionParameters>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
                final PsiElement psiElement = parameters.getPosition();

                if (psiElement instanceof PsiComment) {
                    final PsiFile sqlFile = psiElement.getContainingFile();

                    final PsiElement commentCandidate = sqlFile.getChildren()[0];

                    if (commentCandidate.equals(psiElement)) {
                        String value = commentCandidate.getText().substring(2).trim();

                        if (!value.isEmpty() && value.startsWith("metadata")) {

                            Set<PsiClass> metadatas = new HashSet<>();

                            AllClassesGetter.processJavaClasses(parameters, new PrefixMatcher("Metadata") {
                                @Override
                                public boolean prefixMatches(@NotNull String name) {
                                    return name.endsWith("Metadata");
                                }

                                @NotNull
                                @Override
                                public PrefixMatcher cloneWithPrefix(@NotNull String prefix) {
                                    return this;
                                }
                            }, true, new Consumer<PsiClass>() {
                                @Override
                                public void consume(PsiClass psiClass) {
                                    result.addElement(new JavaPsiClassReferenceElement(psiClass));
                                }
                            });
                        }
                    }
                }

                if (psiElement instanceof SqlStringTokenElement) {
                    final SqlStringTokenElement alias = (SqlStringTokenElement) psiElement;

                    final String value = alias.getTokenText();

                    if (value.isEmpty()) {
                        final PsiFile sqlFile = psiElement.getContainingFile();

                        final PsiElement commentCandidate = sqlFile.getChildren()[0];

                        String metadata = commentCandidate.getText().substring(2).trim();

                        if (!metadata.isEmpty() && metadata.startsWith("metadata")) {
                            metadata = metadata.substring(8).trim();

                            PsiClass[] names = PsiShortNamesCache
                                    .getInstance(alias.getProject())
                                    .getClassesByName(metadata.substring(metadata.lastIndexOf('.') + 1), GlobalSearchScope.allScope(psiElement.getProject()));

                            for (PsiField psiField : names[0].getFields()) {
                                result.addElement(LookupElementBuilder.create(psiField.getInitializer().getText().replaceAll("\"","")));
                            }
                        }
                    }
                }
            }
        });
    }
}

