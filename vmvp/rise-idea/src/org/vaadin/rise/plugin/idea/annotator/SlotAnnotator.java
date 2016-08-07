package org.vaadin.rise.plugin.idea.annotator;

import com.intellij.codeInsight.daemon.impl.GlobalUsageHelper;
import com.intellij.find.findUsages.FindUsagesOptions;
import com.intellij.find.findUsages.JavaFindUsagesHelper;
import com.intellij.find.findUsages.JavaVariableFindUsagesOptions;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.impl.search.ConstructorReferencesSearchHelper;
import com.intellij.psi.impl.source.PsiFieldImpl;
import com.intellij.psi.search.PsiSearchHelper;
import com.intellij.psi.search.searches.ClassInheritorsSearch;
import com.intellij.usageView.UsageInfo;
import com.intellij.util.Processor;
import freemarker.template.Configuration;
import org.jetbrains.annotations.NotNull;
import org.vaadin.rise.plugin.idea.generator.SlotImplementationGenerator;
import org.vaadin.rise.slot.NestedSlot;
import org.vaadin.rise.slot.annotation.Slot;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by oem on 8/4/16.
 */
public class SlotAnnotator implements Annotator {
    private final Configuration freeMarkerCfg;
    private final SlotImplementationGenerator slotImplementationGenerator;

    public SlotAnnotator() {

        freeMarkerCfg = new Configuration(Configuration.VERSION_2_3_0);
        freeMarkerCfg.setClassForTemplateLoading(this.getClass(), "/template");
        freeMarkerCfg.setDefaultEncoding("UTF-8");
        freeMarkerCfg.setLogTemplateExceptions(false);

        slotImplementationGenerator = new SlotImplementationGenerator(freeMarkerCfg);
    }

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof PsiField && NestedSlot.class.getName().equals(((PsiFieldImpl) element).getType().getCanonicalText())) {
            final PsiClass presenterClass = (PsiClass) element.getParent();

            final PsiClass priRiseModule = findRiseModule(presenterClass.getName(), presenterClass.getContainingFile().getParent());

            final AtomicBoolean slotAlreadyRegistered = new AtomicBoolean();

            JavaFindUsagesHelper.processElementUsages(element, new JavaVariableFindUsagesOptions(element.getProject()), usageInfo -> {
                if (!slotAlreadyRegistered.get()) {
                    if (priRiseModule.getContainingFile().equals(usageInfo.getFile())) {
                        slotAlreadyRegistered.set(true);
                    }
                }
                return slotAlreadyRegistered.get();
            });

            if (!slotAlreadyRegistered.get()) {
                TextRange range = new TextRange(
                        element
                                .getTextRange()
                                .getStartOffset(),
                        element
                                .getTextRange()
                                .getEndOffset()
                );

                holder
                        .createErrorAnnotation(range, "Cannot find implementation")
                        .registerFix(new ImplementsSlotFix(presenterClass, priRiseModule, (PsiField) element, slotImplementationGenerator));
            }
        }
    }

    private PsiClass findRiseModule(String psiPresenter, PsiDirectory psiDirectory) {
        return ((PsiJavaFile) psiDirectory.findFile(psiPresenter.substring(0, psiPresenter.length() -9) + "DaggerModule.java")).getClasses()[0];
    }
}
