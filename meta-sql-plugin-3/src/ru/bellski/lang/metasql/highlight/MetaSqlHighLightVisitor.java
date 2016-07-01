package ru.bellski.lang.metasql.highlight;

import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.codeInsight.daemon.impl.HighlightInfoType;
import com.intellij.codeInsight.daemon.impl.HighlightVisitor;
import com.intellij.codeInsight.daemon.impl.analysis.HighlightInfoHolder;
import com.intellij.codeInsight.daemon.impl.quickfix.QuickFixAction;
import com.intellij.codeInspection.HintAction;
import com.intellij.lang.injection.InjectedLanguageManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import ru.bellski.lang.metasql.MetaSqlFile;
import ru.bellski.lang.metasql.MetaSqlTokenTypes;
import ru.bellski.lang.metasql.actions.AddImportQuestionAction;
import ru.bellski.lang.metasql.psi.*;

/**
 * Created by oem on 6/9/16.
 */
public class MetaSqlHighLightVisitor extends MetaSqlVisitor implements HighlightVisitor {
    private final PsiResolveHelper resolveHelper;
    private HighlightInfoHolder myHolder;
    private PsiFile hostFile;
    private MetaSqlFile metaSqlFile;

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
        metaSqlFile = (MetaSqlFile) file;
        metaSqlFile.setHasErrors(false);

        myHolder = holder;
        try {
            action.run();
        } finally {
            myHolder = null;
        }

        return true;
    }

    @Override
    public void visitTypeElement(@NotNull MetaSqlTypeElement o) {
        if (o.getCodeReferenceElement().resolve() == null) {
            final MetaSqlCodeReferenceElement codeReference = o.getCodeReferenceElement();
            final HighlightInfo info = HighlightInfo.newHighlightInfo(HighlightInfoType.WRONG_REF).range(codeReference.getIdentifier()).descriptionAndTooltip("Cannot resolve").create();

            myHolder.add(info);
            metaSqlFile.setHasErrors(true);

            QuickFixAction.registerQuickFixAction(info, new Import(o.getCodeReferenceElement()));
        }
        super.visitTypeElement(o);
    }

    @Override
    public void visitImportReferenceElement(@NotNull MetaSqlImportReferenceElement o) {
        if (o.resolve() == null) {
            final HighlightInfo info = HighlightInfo.newHighlightInfo(HighlightInfoType.WRONG_REF).range(o).descriptionAndTooltip("Cannot resolve import").create();

            myHolder.add(info);
            metaSqlFile.setHasErrors(true);
        }
        super.visitImportReferenceElement(o);
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

        private final MetaSqlCodeReferenceElement ref;

        public Import(MetaSqlCodeReferenceElement ref) {
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
            new AddImportQuestionAction(project, ref, editor, (MetaSqlFile) ref.getContainingFile(), ref.getResolveCandidates()).execute();
        }

        @Override
        public boolean startInWriteAction() {
            return false;
        }
    }
}
