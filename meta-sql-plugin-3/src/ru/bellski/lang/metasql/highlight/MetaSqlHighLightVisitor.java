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
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiResolveHelper;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import ru.bellski.lang.metasql.MetaSqlFile;
import ru.bellski.lang.metasql.psi.MetaSqlCodeReferenceElement;
import ru.bellski.lang.metasql.psi.MetaSqlMetadataClassAccess;
import ru.bellski.lang.metasql.psi.MetaSqlVisitor;

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
        }
        finally {
            myHolder = null;
        }

        return true;
    }


    @Override
    public void visitMetadataClassAccess(@NotNull MetaSqlMetadataClassAccess o) {
        if (o.resolve() == null) {
            final  HighlightInfo info = HighlightInfo.newHighlightInfo(HighlightInfoType.WRONG_REF).range(o).descriptionAndTooltip("Cannot resolve SqlMetadata class").create();
            myHolder.add(info);
            metaSqlFile.setHasErrors(true);
        }

        super.visitMetadataClassAccess(o);
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
