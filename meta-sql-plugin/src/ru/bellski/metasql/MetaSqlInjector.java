package ru.bellski.metasql;

import com.intellij.codeInsight.folding.CodeFoldingManager;
import com.intellij.lang.injection.InjectedLanguageManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.sql.psi.SqlFile;
import com.intellij.sql.psi.SqlTokens;
import com.intellij.sql.psi.impl.SqlTokenElement;
import com.intellij.sql.psi.impl.SqlTokenType;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.lang.MetaSqlLanguage;

import java.util.Arrays;

/**
 * Created by Aleksandr on 21.05.2016.
 */
public class MetaSqlInjector implements LanguageInjector {

    @Override
    public void getLanguagesToInject(@NotNull PsiLanguageInjectionHost host, @NotNull InjectedLanguagePlaces injectionPlacesRegistrar) {
        if (host.getContainingFile() instanceof SqlFile) {
            if (host instanceof PsiComment) {
                if (SqlTokens.SQL_BLOCK_COMMENT == ((PsiComment) host).getTokenType()) {
                    if (host.equals(PsiTreeUtil.findChildOfType(host.getContainingFile(), PsiComment.class))) {
                        injectionPlacesRegistrar.addPlace(MetaSqlLanguage.INSTANCE, new TextRange(2, host.getTextLength() - 2), null, null);
                    }
                }
            }
        }
    }
}
