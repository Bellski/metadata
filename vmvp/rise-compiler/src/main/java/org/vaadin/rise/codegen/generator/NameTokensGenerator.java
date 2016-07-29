package org.vaadin.rise.codegen.generator;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.vaadin.rise.codegen.model.NameTokensModel;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.List;

/**
 * Created by oem on 7/28/16.
 */
public class NameTokensGenerator extends Generator<NameTokensModel> {

	public NameTokensGenerator(Configuration freemarker, Filer filer) {
		super(freemarker, filer);
	}

	@Override
	public List<JavaFileObject> generate(List<NameTokensModel> nameTokensModels) throws IOException, TemplateException {
		return super.generate("TokenNames.ftl", nameTokensModels);
	}

}
