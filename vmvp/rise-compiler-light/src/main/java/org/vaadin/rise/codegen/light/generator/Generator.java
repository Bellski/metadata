package org.vaadin.rise.codegen.light.generator;

import com.google.testing.compile.JavaFileObjects;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.vaadin.rise.codegen.light.model.JFOModel;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oem on 7/18/16.
 */
public abstract class Generator<JFOModel_ extends JFOModel> {
	private final Configuration freemarker;
	private final Filer filer;

	public Generator(Configuration freemarker, Filer filer) {
		this.freemarker = freemarker;
		this.filer = filer;
	}

	public abstract List<JavaFileObject> generate(List<JFOModel_> models) throws IOException, TemplateException;

	List<JavaFileObject> generate(String templateName, List<JFOModel_> models) throws IOException, TemplateException {
		final List<JavaFileObject> jfos = new ArrayList<>();

		for (JFOModel model : models) {
			final Template template = freemarker.getTemplate(templateName);

			final JavaFileObject sourceJfo = filer.createSourceFile(model.getFullyQualifiedName());

			try(Writer inMemory = new StringWriter();  Writer sourceOut = sourceJfo.openWriter()) {
				template.process(model, inMemory);
				template.process(model, sourceOut);

				jfos.add(JavaFileObjects.forSourceLines(model.getFullyQualifiedName(), inMemory.toString()));
			}
		}

		return jfos;
	}
}
