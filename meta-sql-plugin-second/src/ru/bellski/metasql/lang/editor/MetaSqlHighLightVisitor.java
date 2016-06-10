package ru.bellski.metasql.lang.editor;

import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.codeInsight.daemon.impl.HighlightInfoType;
import com.intellij.codeInsight.daemon.impl.HighlightVisitor;
import com.intellij.codeInsight.daemon.impl.analysis.HighlightInfoHolder;
import com.intellij.lang.injection.InjectedLanguageManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiResolveHelper;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import com.intellij.util.FileContentUtil;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.lang.MetaSqlFile;
import ru.bellski.metasql.lang.psi.MetaSqlMetadata;
import ru.bellski.metasql.lang.psi.MetaSqlParametersDefinition;
import ru.bellski.metasql.lang.psi.MetaSqlVisitor;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by oem on 6/9/16.
 */
public class MetaSqlHighLightVisitor extends MetaSqlVisitor implements HighlightVisitor {
    private final PsiResolveHelper resolveHelper;
    private HighlightInfoHolder myHolder;
    private PsiFile hostFile;

    protected MetaSqlHighLightVisitor(@NotNull PsiResolveHelper resolveHelper) {
        this.resolveHelper = resolveHelper;
    }

    @Override
    public boolean suitableForFile(@NotNull PsiFile file) {
        return file instanceof MetaSqlFile && InjectedLanguageManager.getInstance(file.getProject()).isInjectedFragment(file);
    }

    @Override
    public void visit(@NotNull PsiElement element) {
        element.accept(this);
    }

    @Override
    public boolean analyze(@NotNull PsiFile file, boolean updateWholeFile, @NotNull HighlightInfoHolder holder, @NotNull Runnable action) {
        hostFile = InjectedLanguageManager.getInstance(file.getProject()).getTopLevelFile(file);

        myHolder = holder;
        try {
            action.run();
        }
        finally {
            myHolder = null;
        }
        return true;
    }

    @Override
    public void visitMetadata(@NotNull MetaSqlMetadata o) {
        if (o.resolve() == null) {
            myHolder.add(HighlightInfo.newHighlightInfo(HighlightInfoType.WRONG_REF).range(o).descriptionAndTooltip("Cannot resolve SqlMetadata class").create());
        }
    }

    @NotNull
    @Override
    public HighlightVisitor clone() {
        return new MetaSqlHighLightVisitor(resolveHelper);
    }

    @Override
    public int order() {
        return 0;
    }
}
