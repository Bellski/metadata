package org.vaadin.rise.codegen.step;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import dagger.internal.codegen.ComponentProcessor;
import freemarker.template.TemplateException;
import org.vaadin.rise.annotation.ApplicationEntry;
import org.vaadin.rise.codegen.generator.ModuleGenerator;
import org.vaadin.rise.codegen.generator.NameTokensGenerator;
import org.vaadin.rise.codegen.helpers.Compilation;
import org.vaadin.rise.codegen.model.FqnHolder;
import org.vaadin.rise.codegen.model.ModuleModel;
import org.vaadin.rise.codegen.model.NameTokensModel;
import org.vaadin.rise.codegen.model.NestedSlotModel;
import org.vaadin.rise.core.annotation.NoParent;
import org.vaadin.rise.core.annotation.RiseModule;
import org.vaadin.rise.core.annotation.RiseModuleHelper;
import org.vaadin.rise.security.annotation.DefaultGateKeeper;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by oem on 7/29/16.
 */
public class RiseModuleProcessingStep implements BasicAnnotationProcessor.ProcessingStep {
	private final Map<FqnHolder, List<NestedSlotModel>> slotGraph;
	private final ModuleGenerator moduleGenerator;
	private Set<String> places;
	private NameTokensGenerator nameTokensGenerator;
	private final List<JavaFileObject> jfosForDaggerGeneration;
	private final Types types;
	private final Elements elements;
	private String packageName;
	boolean hasApplicationEntry;
	private Element defaultGateKeeperElement;

	public RiseModuleProcessingStep(Map<FqnHolder, List<NestedSlotModel>> slotGraph,
									ModuleGenerator moduleGenerator,
									Set<String> places,
									NameTokensGenerator nameTokensGenerator,
									List<JavaFileObject> jfosForDaggerGeneration,
									Types types,
									Elements elements) {
		this.slotGraph = slotGraph;
		this.moduleGenerator = moduleGenerator;
		this.places = places;
		this.nameTokensGenerator = nameTokensGenerator;
		this.jfosForDaggerGeneration = jfosForDaggerGeneration;
		this.types = types;
		this.elements = elements;
	}

	@Override
	public Set<? extends Class<? extends Annotation>> annotations() {
		return ImmutableSet.of(RiseModule.class, DefaultGateKeeper.class);
	}

	@Override
	public Set<Element> process(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {
		if (elementsByAnnotation.containsKey(DefaultGateKeeper.class)) {
			defaultGateKeeperElement = elementsByAnnotation.get(DefaultGateKeeper.class).iterator().next();
		}



		final Map<ModuleModel, List<ModuleModel>> moduleModelGraph = buildModuleGraph(elementsByAnnotation);
		moduleModelGraph.forEach(ModuleModel::include);

		final NameTokensModel nameTokensModel = new NameTokensModel(
			packageName,
			places
		);

		try {
			jfosForDaggerGeneration.addAll(moduleGenerator.generate(new ArrayList<>(moduleModelGraph.keySet())));
			jfosForDaggerGeneration.addAll(nameTokensGenerator.generate(Collections.singletonList(nameTokensModel)));

			if (!hasApplicationEntry) {
				Compilation.compile(Collections.singleton(new ComponentProcessor()), Collections.emptyList(), jfosForDaggerGeneration);
			}

		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}

		return ImmutableSet.of();
	}

	private Map<ModuleModel, List<ModuleModel>> buildModuleGraph(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {
		final Map<ModuleModel, List<ModuleModel>> moduleModelGraph = new HashMap<>();

		elementsByAnnotation
			.get(RiseModule.class)
			.stream()
			.forEach(riseModuleElement -> {
				final ModuleModel moduleModel = ModuleModel.create(elements, types, slotGraph, riseModuleElement, defaultGateKeeperElement);
				moduleModelGraph.put(moduleModel, new ArrayList<>());
			});

		for (ModuleModel module : moduleModelGraph.keySet()) {
			final ModuleModel parent = getParentModule(module);

			if (parent != null) {

				if (parent.getExtendsModuleElement().getAnnotation(ApplicationEntry.class) != null) {
					packageName = parent.getPackageName();
					hasApplicationEntry = true;
				}

				List<ModuleModel> parentChildren = moduleModelGraph.get(parent);
				parentChildren.add(module);
			} else if (packageName == null){
				packageName = module.getPackageName();
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
			parent = ModuleModel.create(elements, types, slotGraph, parentElement, defaultGateKeeperElement);
		}

		return parent;
	}
}
