package org.vaadin.rise.plugin.idea.annotator;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.vaadin.rise.plugin.idea.generator.SlotImplementationGenerator;
import org.vaadin.rise.plugin.idea.model.PsiClassModel;
import org.vaadin.rise.plugin.idea.model.SlotImplementationModel;
import org.vaadin.rise.plugin.idea.model.SlotType;

/**
 * Created by oem on 8/4/16.
 */
public class ImplementsSlotFix implements IntentionAction {


    private PsiClass slotInterface;
    private SlotImplementationGenerator slotImplementationGenerator;

    public ImplementsSlotFix(PsiClass slotInterface, SlotImplementationGenerator slotImplementationGenerator) {
        this.slotInterface = slotInterface;
        this.slotImplementationGenerator = slotImplementationGenerator;
    }

    @Nls
    @NotNull
    @Override
    public String getText() {
        return "Implements";
    }

    @Nls
    @NotNull
    @Override
    public String getFamilyName() {
        return "Implements";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        return true;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
        final PsiElementFactory elemFactory = JavaPsiFacade.getElementFactory(project);
        final PsiFileFactory psiFileFactory = PsiFileFactory.getInstance(project);

        final PsiClass psiPresenter = PsiTreeUtil.getParentOfType(slotInterface, PsiClass.class);

        final SlotImplementationModel slotImplementationModel = new SlotImplementationModel(
                psiPresenter.getName() + slotInterface.getName(),
                new PsiClassModel(
                        psiPresenter.getName() + "." + slotInterface.getName(),
                        getPackage(slotInterface.getQualifiedName())
                ),
                new PsiClassModel(
                        psiPresenter.getName(),
                        getPackage(psiPresenter.getQualifiedName())
                ),
                SlotType.NESTED_SLOT
        );

        final PsiClass psiRiseModule = findRiseModule(psiPresenter.getName(), file.getParent());

        ApplicationManager.getApplication().invokeAndWait(() -> new WriteCommandAction<Void>(project) {

            @Override
            protected void run(@NotNull Result<Void> result) throws Throwable {
                final PsiJavaFile slotImplementation = (PsiJavaFile) slotImplementationGenerator.generate(slotImplementationModel, psiFileFactory);

                psiRiseModule.add(slotImplementation.getClasses()[0]);
                psiRiseModule.add(slotImplementationGenerator.generate(slotImplementationModel, elemFactory));

                CodeStyleManager.getInstance(project).reformat(psiRiseModule);
                JavaCodeStyleManager.getInstance(project).optimizeImports(psiRiseModule.getContainingFile());
            }
        }.execute(), ModalityState.NON_MODAL);
    }

    @Override
    public boolean startInWriteAction() {
        return false;
    }

    private String getPackage(String fqn) {
        return fqn.substring(0, fqn.lastIndexOf('.'));
    }

    private PsiClass findRiseModule(String psiPresenter, PsiDirectory psiDirectory) {
        return ((PsiJavaFile) psiDirectory.findFile(psiPresenter.substring(0, psiPresenter.length() -9) + "DaggerModule.java")).getClasses()[0];
    }
}
