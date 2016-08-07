package org.vaadin.rise.plugin.idea.generator;

import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.vaadin.rise.plugin.idea.model.PsiClassModel;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by oem on 8/4/16.
 */
public abstract class Generator {
	protected Configuration freeMarkerConfig;

	public Generator(Configuration freeMarkerConfig) {
		this.freeMarkerConfig = freeMarkerConfig;
	}

	PsiFile generate(String templateName, PsiClassModel psiClassModel, PsiFileFactory psiFileFactory) throws IOException, TemplateException {
		final Template template = freeMarkerConfig.getTemplate(templateName);

		try(final Writer writer = new StringWriter()) {
			template.process(psiClassModel, writer);

			String stringTemplate = writer.toString();

			stringTemplate = StringUtil.convertLineSeparators(stringTemplate, true);

			return psiFileFactory.createFileFromText(
					psiClassModel.getName().concat(".java"),
					JavaLanguage.INSTANCE,
					stringTemplate
			);
		}
	}

	public abstract PsiFile generate(PsiClassModel psiClassModel, PsiFileFactory psiFileFactory) throws IOException, TemplateException;
}
