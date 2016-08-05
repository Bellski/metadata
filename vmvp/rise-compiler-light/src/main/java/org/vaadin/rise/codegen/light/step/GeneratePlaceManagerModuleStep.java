package org.vaadin.rise.codegen.light.step;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.vaadin.rise.codegen.light.generator.PlaceManagerModuleGenerator;
import org.vaadin.rise.codegen.light.model.PlaceManagerModuleModel;
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
public class GeneratePlaceManagerModuleStep implements BasicAnnotationProcessor.ProcessingStep {

	private final PlaceManagerModuleModel placeManagerModuleModel;
	private final Configuration cfg;
	private final Filer filer;
	private final Messager messager;

	public GeneratePlaceManagerModuleStep(PlaceManagerModuleModel placeManagerModuleModel, Configuration cfg, Filer filer, Messager messager) {
		this.placeManagerModuleModel = placeManagerModuleModel;
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
			new PlaceManagerModuleGenerator(cfg, filer).generate(Collections.singletonList(placeManagerModuleModel));
		} catch (IOException | TemplateException e) {
			messager.printMessage(Diagnostic.Kind.ERROR, e.getMessage());
		}

		return ImmutableSet.of();
	}
}
