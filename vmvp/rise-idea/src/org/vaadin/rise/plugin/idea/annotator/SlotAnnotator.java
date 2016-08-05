package org.vaadin.rise.plugin.idea.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.searches.ClassInheritorsSearch;
import com.intellij.psi.util.PsiTreeUtil;
import freemarker.template.Configuration;
import org.jetbrains.annotations.NotNull;
import org.vaadin.rise.plugin.idea.generator.SlotImplementationGenerator;
import org.vaadin.rise.slot.annotation.Slot;

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
        if (element instanceof PsiAnnotation && Slot.class.getName().equals(((PsiAnnotation) element).getQualifiedName())) {
            final PsiClass slotInterface = PsiTreeUtil.getParentOfType(element, PsiClass.class);

            if (noOneImplementsSlot(slotInterface)) {
                TextRange range = new TextRange(slotInterface.getTextRange().getStartOffset(),
                        slotInterface.getTextRange().getEndOffset());

                holder
                        .createErrorAnnotation(range, "Cannot find implementation")
                        .registerFix(new ImplementsSlotFix(slotInterface, slotImplementationGenerator));
            }
        }
    }

    private boolean noOneImplementsSlot(PsiClass slotInterface) {
        return ClassInheritorsSearch.search(slotInterface).findFirst() == null;
    }
}
