package org.vaadin.rise.codegen.generator;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.vaadin.rise.codegen.model.RiseBootstrapModuleModel;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.List;

/**
 * Created by oem on 7/18/16.
 */
public class RiseBootstrapModuleGenerator extends Generator<RiseBootstrapModuleModel> {

	public RiseBootstrapModuleGenerator(Configuration freemarker, Filer filer) {
		super(freemarker, filer);
	}

	@Override
	public List<JavaFileObject> generate(List<RiseBootstrapModuleModel> riseBootstrapModuleModels) throws IOException, TemplateException {
		return generate("RiseBootstrapModule.ftl", riseBootstrapModuleModels);
	}
}
