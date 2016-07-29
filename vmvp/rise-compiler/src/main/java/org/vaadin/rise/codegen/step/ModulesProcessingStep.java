package org.vaadin.rise.codegen.step;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import dagger.internal.codegen.ComponentProcessor;
import freemarker.template.TemplateException;
import org.vaadin.rise.annotation.ApplicationEntry;
import org.vaadin.rise.codegen.generator.ModuleGenerator;
import org.vaadin.rise.codegen.generator.NameTokensGenerator;
import org.vaadin.rise.codegen.generator.PlaceManagerModuleGenerator;
import org.vaadin.rise.codegen.helpers.Compilation;
import org.vaadin.rise.codegen.model.FqnHolder;
import org.vaadin.rise.codegen.model.ModuleModel;
import org.vaadin.rise.codegen.model.NestedSlotModel;
import org.vaadin.rise.codegen.model.PlaceManagerModuleModel;
import org.vaadin.rise.codegen.model.aNewOne.PresenterData;
import org.vaadin.rise.security.annotation.DefaultGateKeeper;

import javax.lang.model.element.Element;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by oem on 7/15/16.
 */
public class ModulesProcessingStep implements BasicAnnotationProcessor.ProcessingStep {

	private final Map<FqnHolder, List<NestedSlotModel>> slotGraph;
	private final ModuleGenerator moduleGenerator;
	private List<JavaFileObject> forDaggerGeneration;
	private Map<FqnHolder, PresenterData> presenterDatas;
	private List<JavaFileObject> jfosForDaggerGeneration;
	private final NameTokensGenerator nameTokensGenerator;
	private PlaceManagerModuleGenerator placeManagerModuleGenerator;
	private final Set<String> places;
	private final Types types;
	private final Elements elements;
	private ModuleModel entryModuleModel;


	public ModulesProcessingStep(Map<FqnHolder, List<NestedSlotModel>> slotGraph, List<JavaFileObject> jfosForDaggerGeneration, NameTokensGenerator nameTokensGenerator, PlaceManagerModuleGenerator placeManagerModuleGenerator, Set<String> places, Types types, Elements elements, ModuleGenerator moduleGenerator, List<JavaFileObject> forDaggerGeneration, Map<FqnHolder, PresenterData> presenterDatas) {
		this.slotGraph = slotGraph;
		this.jfosForDaggerGeneration = jfosForDaggerGeneration;
		this.nameTokensGenerator = nameTokensGenerator;
		this.placeManagerModuleGenerator = placeManagerModuleGenerator;
		this.places = places;
		this.types = types;
		this.elements = elements;
		this.moduleGenerator = moduleGenerator;
		this.forDaggerGeneration = forDaggerGeneration;
		this.presenterDatas = presenterDatas;
	}

	@Override
	public Set<? extends Class<? extends Annotation>> annotations() {
		return ImmutableSet.of(
			ApplicationEntry.class,
			DefaultGateKeeper.class
		);
	}

	@Override
	public Set<Element> process(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {
		final Element applicatioEntryElement = elementsByAnnotation.get(ApplicationEntry.class).iterator().next();

		final ModuleModel applicationModuleModel = ModuleModel.create(elements, types, slotGraph, applicatioEntryElement, null);

		final PlaceManagerModuleModel placeManagerModuleModel = new PlaceManagerModuleModel(applicationModuleModel.getPackageName());

		if (elementsByAnnotation.containsKey(DefaultGateKeeper.class)) {
			placeManagerModuleModel.setDefaultGateKeeper(FqnHolder.buildJavaCompatibleFQN(elementsByAnnotation.get(DefaultGateKeeper.class).iterator().next()));
		}

		for (PresenterData presenterData : presenterDatas.values()) {
			if (presenterData.isDefaultPlace()) {
				placeManagerModuleModel.setDefaultPlaceNameToken(presenterData.getPlace());
			}

			if (presenterData.isAuthorizePlace()) {
				placeManagerModuleModel.setUnauthorizedPlaceNameToken(presenterData.getPlace());
			}

			if (presenterData.isErrorPlace()) {
				placeManagerModuleModel.setErrorPlaceNameToken(presenterData.getPlace());
			}

			if (presenterData.isPlaceBus()) {
				try {
					applicatioEntryElement.getAnnotation(ApplicationEntry.class).usePlaceBus();
				} catch (MirroredTypeException e) {
					placeManagerModuleModel.setPlaceBus(FqnHolder.buildJavaCompatibleFQN(types.asElement(e.getTypeMirror())));
				}
			}
		}


		try {
			jfosForDaggerGeneration.addAll(placeManagerModuleGenerator.generate(Collections.singletonList(placeManagerModuleModel)));
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}

		Compilation.compile(Collections.singleton(new ComponentProcessor()), Collections.emptyList(), jfosForDaggerGeneration);

		return ImmutableSet.of();
	}

}
