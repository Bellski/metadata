package org.vaadin.rise.codegen.light.step;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.vaadin.rise.codegen.light.generator.NameTokenPartsGenerator;
import org.vaadin.rise.codegen.light.model.NameTokenPartsModel;
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
public class GenerateNameTokenPartsStep implements BasicAnnotationProcessor.ProcessingStep {
	private NameTokenPartsModel nameTokenPartsModel;
	private final Configuration cfg;
	private final Filer filer;
	private Messager messager;

	public GenerateNameTokenPartsStep(NameTokenPartsModel nameTokenPartsModel, Configuration cfg, Filer filer, Messager messager) {
		this.nameTokenPartsModel = nameTokenPartsModel;
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
			new NameTokenPartsGenerator(cfg, filer).generate(Collections.singletonList(nameTokenPartsModel));
		} catch (IOException | TemplateException e) {
			messager.printMessage(Diagnostic.Kind.ERROR, e.getMessage());
		}

		return ImmutableSet.of();
	}
}
