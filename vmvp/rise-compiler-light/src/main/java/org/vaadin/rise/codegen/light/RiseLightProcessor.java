package org.vaadin.rise.codegen.light;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;
import freemarker.template.Configuration;
import org.vaadin.rise.codegen.light.model.NameTokenPartsModel;
import org.vaadin.rise.codegen.light.model.PlaceManagerModuleModel;
import org.vaadin.rise.codegen.light.model.PlacesModuleModel;
import org.vaadin.rise.codegen.light.step.CollectData;
import org.vaadin.rise.codegen.light.step.GenerateNameTokenPartsStep;
import org.vaadin.rise.codegen.light.step.GeneratePlaceManagerModuleStep;
import org.vaadin.rise.codegen.light.step.GeneratePlacesModuleStep;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.Processor;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Created by oem on 8/5/16.
 */
@AutoService(Processor.class)
public class RiseLightProcessor extends BasicAnnotationProcessor {
	private final PlaceManagerModuleModel placeManagerModuleModel = new PlaceManagerModuleModel();
	private final NameTokenPartsModel nameTokenPartsModel = new NameTokenPartsModel();
	private final PlacesModuleModel placesModuleModel = new PlacesModuleModel();

	private final Configuration cfg;
	private Messager messager;
	private Types types;
	private Elements elements;
	private Filer filer;

	public RiseLightProcessor() {
		cfg = new Configuration(Configuration.VERSION_2_3_0);
		cfg.setClassForTemplateLoading(this.getClass(), "/templates");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLogTemplateExceptions(false);
	}

	@Override
	protected Iterable<? extends ProcessingStep> initSteps() {
		messager = processingEnv.getMessager();
		types = processingEnv.getTypeUtils();
		elements = processingEnv.getElementUtils();
		filer = processingEnv.getFiler();

		return ImmutableSet.of(
			new CollectData(nameTokenPartsModel, placeManagerModuleModel, placesModuleModel, elements),
			new GenerateNameTokenPartsStep(nameTokenPartsModel, cfg, filer, messager),
			new GeneratePlaceManagerModuleStep(placeManagerModuleModel, cfg, filer, messager),
			new GeneratePlacesModuleStep(placesModuleModel, cfg, filer, messager)
		);
	}
}
