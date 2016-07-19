package org.vaadin.rise.codegen.generator;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.vaadin.rise.codegen.model.EagerProxiesModel;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.List;

/**
 * Created by oem on 7/18/16.
 */
public class EagerProxiesGenerator extends Generator<EagerProxiesModel> {
	public EagerProxiesGenerator(Configuration freemarker, Filer filer)  {
		super(freemarker, filer);
	}

	@Override
	public List<JavaFileObject> generate(List<EagerProxiesModel> eagerProxiesModels) throws IOException, TemplateException {
		return generate("EagerProxies.ftl", eagerProxiesModels);
	}

}
