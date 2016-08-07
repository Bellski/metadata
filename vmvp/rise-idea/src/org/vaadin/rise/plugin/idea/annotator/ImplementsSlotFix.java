package org.vaadin.rise.plugin.idea.annotator;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.IncorrectOperationException;
import freemarker.template.TemplateException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.vaadin.rise.place.LazyPresenterProvider;
import org.vaadin.rise.plugin.idea.generator.SlotImplementationGenerator;
import org.vaadin.rise.plugin.idea.model.LazyPresenterProviderMethodModel;
import org.vaadin.rise.plugin.idea.model.PsiClassModel;
import org.vaadin.rise.slot.SlotRevealBus;

import java.io.IOException;

/**
 * Created by oem on 8/4/16.
 */
public class ImplementsSlotFix implements IntentionAction {


    private final PsiClass presenterClass;
    private final PsiClass risModule;
    private PsiField slot;
    private SlotImplementationGenerator slotImplementationGenerator;

    public ImplementsSlotFix(PsiClass presenterClass, PsiClass risModule, PsiField slot, SlotImplementationGenerator slotImplementationGenerator) {
        this.presenterClass = presenterClass;
        this.risModule = risModule;
        this.slot = slot;
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
        final JavaPsiFacade psiFasade = JavaPsiFacade.getInstance(project);
        final PsiFileFactory psiFileFactory = PsiFileFactory.getInstance(project);

        if (risModule.findMethodsByName("lazyPresenterProvider", false).length == 0) {
            LazyPresenterProviderMethodModel lazyPresenterProviderMethodModel = new LazyPresenterProviderMethodModel(
                    new PsiClassModel(presenterClass.getName(), StringUtil.getPackageName(presenterClass.getQualifiedName())),
                    new PsiClassModel(presenterClass.getName() + "." + slot.getName(), "")
            );

            ApplicationManager.getApplication().invokeAndWait(() -> new WriteCommandAction<Void>(project) {
                @Override
                protected void run(@NotNull Result<Void> result) throws Throwable {
                    final PsiMethod lazyMethod = slotImplementationGenerator.generate(lazyPresenterProviderMethodModel, elemFactory);
                    risModule.add(lazyMethod);

                    final GlobalSearchScope scope = GlobalSearchScope.moduleWithDependenciesAndLibrariesScope(ModuleUtil.findModuleForPsiElement(file));

                    final PsiJavaFile psiJavaFile = (PsiJavaFile) risModule.getContainingFile();

                    psiJavaFile.importClass(psiFasade.findClass("dagger.multibindings.IntoSet", scope));
                    psiJavaFile.importClass(psiFasade.findClass("dagger.Lazy", scope));
                    psiJavaFile.importClass(psiFasade.findClass(LazyPresenterProvider.class.getName(), scope));
                    psiJavaFile.importClass(psiFasade.findClass(LazyPresenterProvider.class.getName(), scope));
                    psiJavaFile.importClass(psiFasade.findClass(SlotRevealBus.class.getName(), scope));
                }
            }.execute() , ModalityState.NON_MODAL);
        }

    }

    @Override
    public boolean startInWriteAction() {
        return false;
    }

    private PsiClass findRiseModule(String psiPresenter, PsiDirectory psiDirectory) {
        return ((PsiJavaFile) psiDirectory.findFile(psiPresenter.substring(0, psiPresenter.length() -9) + "DaggerModule.java")).getClasses()[0];
    }
}
