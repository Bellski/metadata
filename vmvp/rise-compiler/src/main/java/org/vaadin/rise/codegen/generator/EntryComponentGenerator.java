package org.vaadin.rise.codegen.generator;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.vaadin.rise.codegen.model.EntryComponentModel;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.List;

/**
 * Created by oem on 7/19/16.
 */
public class EntryComponentGenerator extends Generator<EntryComponentModel> {

	public EntryComponentGenerator(Configuration freemarker, Filer filer) {
		super(freemarker, filer);
	}

	@Override
	public List<JavaFileObject> generate(List<EntryComponentModel> entryComponentModels) throws IOException, TemplateException {
		return generate("EntryComponent.ftl", entryComponentModels);
	}
}
