package org.vaadin.rise.codegen.generator;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.vaadin.rise.codegen.model.NestedSlotModel;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.List;

/**
 * Created by oem on 7/18/16.
 */
public class NestedSlotGenerator extends Generator<NestedSlotModel> {
	public NestedSlotGenerator(Configuration freemarker, Filer filer) {
		super(freemarker, filer);
	}

	@Override
	public List<JavaFileObject> generate(List<NestedSlotModel> nestedSlotModels) throws IOException, TemplateException {
		return generate("NestedSlot.ftl", nestedSlotModels);
	}
}
