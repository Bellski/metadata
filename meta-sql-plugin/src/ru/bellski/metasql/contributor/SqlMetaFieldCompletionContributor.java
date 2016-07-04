package ru.bellski.metasql.contributor;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.sql.psi.SqlFile;
import com.intellij.sql.psi.impl.SqlStringTokenElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.MetaSqlFilesCache;
import ru.bellski.metasql.lang.MetaSqlFile;
import ru.bellski.metasql.lang.psi.MetadataReference;

/**
 * Created by oem on 5/18/16.
 */
public class SqlMetaFieldCompletionContributor extends CompletionContributor {

    public SqlMetaFieldCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(SqlStringTokenElement.class), new CompletionProvider<CompletionParameters>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
                final MetaSqlFile metaSql = MetaSqlFilesCache.getInstance(parameters.getPosition().getProject()).findMetaSqlFile((SqlFile) parameters.getOriginalFile());

                final MetadataReference metadataReference = PsiTreeUtil.findChildOfType(metaSql, MetadataReference.class);

                if (metadataReference != null) {
                    final PsiClass metadataClass = (PsiClass) metadataReference.resolve();

                    if (metadataClass != null) {
                        for (PsiField psiField : metadataClass.getFields()) {
                            result.addElement(LookupElementBuilder.create(psiField.getInitializer().getText().replaceAll("\"", "")));
                        }
                    }
                }
            }
        });


    }
}
