package org.vaadin.rise.plugin.idea.generator;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiMethod;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.vaadin.rise.plugin.idea.model.LazyPresenterProviderMethodModel;
import org.vaadin.rise.plugin.idea.model.PsiClassModel;


import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by oem on 8/4/16.
 */
public class SlotImplementationGenerator extends Generator {
    public SlotImplementationGenerator(Configuration freeMarkerConfig) {
        super(freeMarkerConfig);
    }

    @Override
    public PsiFile generate(PsiClassModel psiClassModel, PsiFileFactory psiFileFactory) throws IOException, TemplateException {
        return super.generate("SlotImplementation.ftl", psiClassModel, psiFileFactory);
    }

    public PsiMethod generate(LazyPresenterProviderMethodModel lazyPresenterProviderMethodModel, PsiElementFactory elementFactory) throws IOException, TemplateException {
        final Template template = freeMarkerConfig.getTemplate("ProvidesSlotMethod.ftl");

        try (Writer out = new StringWriter()) {
            template.process(lazyPresenterProviderMethodModel, out);

            return elementFactory.createMethodFromText(StringUtil.convertLineSeparators(out.toString(), true).trim(), null);
        }
    }
}
