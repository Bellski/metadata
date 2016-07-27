package org.vaadin.rise.codegen.generator;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.vaadin.rise.codegen.model.PlaceModel;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.List;

/**
 * Created by oem on 7/18/16.
 */
public class ProxyGenerator extends Generator<PlaceModel> {

	public ProxyGenerator(Configuration freemarker, Filer filer) {
		super(freemarker, filer);
	}

	@Override
	public List<JavaFileObject> generate(List<PlaceModel> proxyModels) throws IOException, TemplateException {
		return generate("Proxy.ftl", proxyModels);
	}

}
