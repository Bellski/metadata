package org.vaadin.rise.codegen.step;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import dagger.internal.codegen.ComponentProcessor;
import freemarker.template.TemplateException;
import org.vaadin.rise.annotation.ApplicationEntry;
import org.vaadin.rise.codegen.generator.EntryComponentGenerator;
import org.vaadin.rise.codegen.generator.ModuleGenerator;
import org.vaadin.rise.codegen.helpers.Compilation;
import org.vaadin.rise.codegen.model.*;
import org.vaadin.rise.core.annotation.NoParent;
import org.vaadin.rise.core.annotation.RiseModule;
import org.vaadin.rise.core.annotation.RiseModuleHelper;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by oem on 7/15/16.
 */
public class ModulesProcessingStep implements BasicAnnotationProcessor.ProcessingStep {

	private final Map<FqnHolder, List<NestedSlotModel>> slotGraph;
	private final ModuleGenerator moduleGenerator;
	private List<JavaFileObject> forDaggerGeneration;
	private EntryComponentGenerator entryComponentGenerator;
	private Map<FqnHolder, PlaceModel> proxyModels;
	private List<JavaFileObject> jfosForDaggerGeneration;
	private final Types types;
	private final Elements elements;
	private ModuleModel entryModuleModel;

	public ModulesProcessingStep(Map<FqnHolder, List<NestedSlotModel>> slotGraph, Map<FqnHolder, PlaceModel> proxyModels, List<JavaFileObject> jfosForDaggerGeneration, Types types, Elements elements, ModuleGenerator moduleGenerator, List<JavaFileObject> forDaggerGeneration, EntryComponentGenerator entryComponentGenerator) {
		this.slotGraph = slotGraph;
		this.proxyModels = proxyModels;
		this.jfosForDaggerGeneration = jfosForDaggerGeneration;
		this.types = types;
		this.elements = elements;
		this.moduleGenerator = moduleGenerator;
		this.forDaggerGeneration = forDaggerGeneration;
		this.entryComponentGenerator = entryComponentGenerator;
	}

	@Override
	public Set<? extends Class<? extends Annotation>> annotations() {
		return ImmutableSet.of(
			ApplicationEntry.class,
			RiseModule.class
		);
	}

	@Override
	public Set<Element> process(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {
		final Map<ModuleModel, List<ModuleModel>> moduleModelGraph = buildModuleGraph(elementsByAnnotation);
		moduleModelGraph.forEach(ModuleModel::include);

		try {
			jfosForDaggerGeneration.addAll(moduleGenerator.generate(new ArrayList<>(moduleModelGraph.keySet())));
			jfosForDaggerGeneration.addAll(
				entryComponentGenerator.generate(
					Collections.singletonList(new EntryComponentModel(entryModuleModel))
				)
			);
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}

		Compilation.compile(Collections.singleton(new ComponentProcessor()), Collections.emptyList(), jfosForDaggerGeneration);

		return ImmutableSet.of();
	}

	private Map<ModuleModel, List<ModuleModel>> buildModuleGraph(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {
		final Map<ModuleModel, List<ModuleModel>> moduleModelGraph = new HashMap<>();

		for (Element riseModuleElement : elementsByAnnotation.get(RiseModule.class)) {

			final ModuleModel moduleModel = ModuleModel.create(types, slotGraph, proxyModels, riseModuleElement);

			if (riseModuleElement.getAnnotation(ApplicationEntry.class) != null) {
				entryModuleModel = moduleModel;
			}

			moduleModelGraph.put(moduleModel, new ArrayList<>());
		}

		for (ModuleModel module : moduleModelGraph.keySet()) {
			final ModuleModel parent = getParentModule(module);
			if (parent != null) {
				List<ModuleModel> parentChildren = moduleModelGraph.get(parent);
				parentChildren.add(module);
			}
		}

		return moduleModelGraph;
	}

	public ModuleModel getParentModule(ModuleModel module) {
		final TypeMirror NO_PARENT = elements.getTypeElement(NoParent.class.getName()).asType();

		final RiseModule riseModuleAnnotation = module.getExtendsModuleElement().getAnnotation(RiseModule.class);

		ModuleModel parent = null;

		final TypeMirror parentType = RiseModuleHelper.parentValue(riseModuleAnnotation);

		if (!types.isSameType(parentType, NO_PARENT)) {
			final Element parentElement = types.asElement(parentType);
			parent = ModuleModel.create(types, slotGraph, proxyModels, parentElement);
		}

		return parent;
	}


}
