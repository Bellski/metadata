package org.vaadin.rise.plugin.idea.generator;

import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.vaadin.rise.plugin.idea.model.PsiClassModel;

import java.io.IOException;

/**
 * Created by oem on 8/4/16.
 */
public class PresenterGenerator extends Generator {
    public PresenterGenerator(Configuration freeMarkerConfig) {
        super(freeMarkerConfig);
    }

    @Override
    public PsiFile generate(PsiClassModel psiClassModel, PsiFileFactory psiFileFactory) throws IOException, TemplateException {
        return super.generate("RisePresenter.ftl", psiClassModel, psiFileFactory);
    }
}
