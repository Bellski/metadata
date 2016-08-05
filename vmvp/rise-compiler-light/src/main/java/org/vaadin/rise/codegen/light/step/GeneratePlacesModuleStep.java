package org.vaadin.rise.codegen.light.step;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.vaadin.rise.codegen.light.generator.PlacesModuleGenerator;
import org.vaadin.rise.codegen.light.model.PlacesModuleModel;
import org.vaadin.rise.core.annotation.Presenter;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Set;

/**
 * Created by oem on 8/5/16.
 */
public class GeneratePlacesModuleStep implements BasicAnnotationProcessor.ProcessingStep {
	private final PlacesModuleModel placesModuleModel;
	private final Configuration cfg;
	private final Filer filer;
	private final Messager messager;

	public GeneratePlacesModuleStep(PlacesModuleModel placesModuleModel, Configuration cfg, Filer filer, Messager messager) {
		this.placesModuleModel = placesModuleModel;
		this.cfg = cfg;
		this.filer = filer;
		this.messager = messager;
	}

	@Override
	public Set<? extends Class<? extends Annotation>> annotations() {
		return ImmutableSet.of(Presenter.class);
	}

	@Override
	public Set<Element> process(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {
		try {
			new PlacesModuleGenerator(cfg, filer).generate(Collections.singletonList(placesModuleModel));
		} catch (IOException | TemplateException e) {
			messager.printMessage(Diagnostic.Kind.ERROR, e.getMessage());
		}
		return ImmutableSet.of();
	}
}
