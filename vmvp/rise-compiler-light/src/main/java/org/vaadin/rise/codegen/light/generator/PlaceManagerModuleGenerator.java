package org.vaadin.rise.codegen.light.generator;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.vaadin.rise.codegen.light.model.PlaceManagerModuleModel;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.List;

/**
 * Created by oem on 8/5/16.
 */
public class PlaceManagerModuleGenerator extends Generator<PlaceManagerModuleModel> {
	public PlaceManagerModuleGenerator(Configuration freemarker, Filer filer) {
		super(freemarker, filer);
	}

	@Override
	public List<JavaFileObject> generate(List<PlaceManagerModuleModel> placeManagerModuleModels) throws IOException, TemplateException {
		return super.generate("PlaceManagerModule.ftl", placeManagerModuleModels);
	}
}
