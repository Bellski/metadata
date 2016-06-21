package ru.bellski.metasql.lang.editor;

import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.codeInsight.daemon.impl.HighlightInfoType;
import com.intellij.codeInsight.daemon.impl.HighlightVisitor;
import com.intellij.codeInsight.daemon.impl.actions.AddImportAction;
import com.intellij.codeInsight.daemon.impl.analysis.HighlightInfoHolder;
import com.intellij.codeInsight.daemon.impl.quickfix.QuickFixAction;
import com.intellij.codeInsight.hint.HintManager;
import com.intellij.codeInsight.hint.QuestionAction;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.HintAction;
import com.intellij.lang.injection.InjectedLanguageManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiResolveHelper;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import com.intellij.util.FileContentUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.lang.MetaSqlElementUtil;
import ru.bellski.metasql.lang.MetaSqlFile;
import ru.bellski.metasql.lang.psi.*;

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
        return file instanceof MetaSqlFile;
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
            final  HighlightInfo info = HighlightInfo.newHighlightInfo(HighlightInfoType.WRONG_REF).range(o).descriptionAndTooltip("Cannot resolve SqlMetadata class").create();
            myHolder.add(info);

            QuickFixAction.registerQuickFixAction(info, new Import(o));
        }
    }

    @Override
    public void visitReturnStatement(@NotNull MetaSqlReturnStatement o) {
        if (!MetaSqlElementUtil.isMetadataClassExistsOrResolve((MetaSqlFile) o.getContainingFile())) {
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

    public class Import implements HintAction {

        private final MetaSqlMetadata ref;

        public Import(MetaSqlMetadata ref) {
            this.ref = ref;
        }

        @Override
        public boolean showHint(@NotNull Editor editor) {
            return false;
        }

        @Nls
        @NotNull
        @Override
        public String getText() {
            return "Import Metadata";
        }

        @Nls
        @NotNull
        @Override
        public String getFamilyName() {
            return "Import Metadata";
        }

        @Override
        public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
            return true;
        }

        @Override
        public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
            new AddImportAction(editor.getProject(), ref, editor, ref.multyResolve()).execute();
        }

        @Override
        public boolean startInWriteAction() {
            return false;
        }
    }
}
