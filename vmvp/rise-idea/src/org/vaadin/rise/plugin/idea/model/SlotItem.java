package org.vaadin.rise.plugin.idea.model;

import com.intellij.psi.PsiClass;

/**
 * Created by oem on 8/4/16.
 */
public class SlotItem {
    private PsiClassModel psiClassModel;
    private PsiClassModel parentModel;
    private PsiClass parentRiseModule;

    public SlotItem(PsiClassModel psiClassModel, PsiClassModel parentModel, PsiClass parentRiseModule) {
        this.psiClassModel = psiClassModel;
        this.parentModel = parentModel;
        this.parentRiseModule = parentRiseModule;
    }

    public PsiClassModel getPsiClassModel() {
        return psiClassModel;
    }

    public PsiClassModel getParentModel() {
        return parentModel;
    }

    public PsiClass getParentRiseModule() {
        return parentRiseModule;
    }

    @Override
    public String toString() {
        return psiClassModel.getFqn();
    }
}
