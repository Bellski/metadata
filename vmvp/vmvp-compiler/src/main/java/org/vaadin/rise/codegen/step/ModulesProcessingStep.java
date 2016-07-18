package org.vaadin.rise.codegen.step;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import dagger.internal.codegen.ComponentProcessor;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.vaadin.rise.annotation.ApplicationEntry;
import org.vaadin.rise.codegen.freemaker.*;
import org.vaadin.rise.codegen.generator.ModuleGenerator;
import org.vaadin.rise.codegen.helpers.Compilation;
import org.vaadin.rise.codegen.metadata.RiseModuleMetadata;
import org.vaadin.rise.core.annotation.NoParent;
import org.vaadin.rise.core.annotation.RiseModule;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.function.Consumer;

import static org.vaadin.rise.codegen.freemaker.ElementsUtils.toFQN;
import static org.vaadin.rise.codegen.freemaker.ElementsUtils.toRiseFQN;

/**
 * Created by oem on 7/15/16.
 */
public class ModulesProcessingStep implements BasicAnnotationProcessor.ProcessingStep {

	private final Map<FQN, List<NestedSlotModel>> slotGraph;
	private final Configuration cfg;
	private Map<RiseModuleMetadata, List<RiseModuleMetadata>> graph;
	private final Types types;
	private final Elements elements;
	private final Filer filer;

	public ModulesProcessingStep(Map<FQN, List<NestedSlotModel>> slotGraph, Map<RiseModuleMetadata, List<RiseModuleMetadata>> graph, Types types, Elements elements, Filer filer, Configuration cfg) {
		this.slotGraph = slotGraph;
		this.graph = graph;
		this.types = types;
		this.elements = elements;
		this.filer = filer;
		this.cfg = cfg;
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
		final Map<RiseModuleMetadata, List<RiseModuleMetadata>> graph = buildModuleGraph(elementsByAnnotation);

		final Set<ModuleModel> moduleModels = new HashSet<>();

		graph.forEach((module, children) -> {
			final Element moduleElement = module.getElement();

			final ModuleModel rootModule =
					new ModuleModel(toRiseFQN(elements, moduleElement), toFQN(elements, moduleElement));

			rootModule.setProvidesView(
					new ProvidesVPModel(toFQN(elements, module.getViewElement()), toFQN(elements, module.getViewElement()))
			);

			rootModule.setProvidesPresenter(
					new ProvidesVPModel(toFQN(elements, module.getPresenterElement()), toFQN(elements, module.getPresenterElement()))
			);


			final List<NestedSlotModel> slots = slotGraph.get(rootModule.getProvidesPresenter().getFQNImpl());

			if (slots != null) {
				for (NestedSlotModel slot : slots) {
					rootModule.addSlot(new ProvidesSlotModel(slot));
				}
			}


			moduleModels.add(rootModule);

			for (RiseModuleMetadata child : children) {
				final Element childElement = child.getElement();

				final ModuleModel childModule =
						new ModuleModel(toRiseFQN(elements, childElement), toFQN(elements, childElement));

				rootModule.include(childModule);
			}
		});


		try {

			final ImmutableCollection<JavaFileObject> jfsos = new ModuleGenerator(cfg, filer).generate(moduleModels, elements);

			Compilation
					.compile(Collections.singletonList(new ComponentProcessor()), Collections.emptyList(), jfsos);

		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}

		return ImmutableSet.of();
	}

	private Map<RiseModuleMetadata, List<RiseModuleMetadata>> buildModuleGraph(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {
		final Map<RiseModuleMetadata, List<RiseModuleMetadata>> graph = new HashMap<>();

		if (elementsByAnnotation.containsKey(ApplicationEntry.class)) {
			final Element element = elementsByAnnotation.get(ApplicationEntry.class).iterator().next();
			final RiseModuleMetadata riseMdduleMetadata = new RiseModuleMetadata(element, true);

			try {
				element.getAnnotation(ApplicationEntry.class).view();
			} catch (MirroredTypeException view){
				riseMdduleMetadata.setViewElement(types.asElement(view.getTypeMirror()));

				try {
					element.getAnnotation(ApplicationEntry.class).presenter();
				} catch (MirroredTypeException presenter){
					riseMdduleMetadata.setPresenterElement(types.asElement(presenter.getTypeMirror()));
				}

			}

			graph.put(riseMdduleMetadata, new ArrayList<>());
		}

		if (elementsByAnnotation.containsKey(RiseModule.class)) {

			elementsByAnnotation.get(RiseModule.class).forEach(element -> {

				final RiseModuleMetadata childRiseModuleMetadata = new RiseModuleMetadata(element);

				try {
					element.getAnnotation(RiseModule.class).view();
				} catch (MirroredTypeException view){
					childRiseModuleMetadata.setViewElement(types.asElement(view.getTypeMirror()));

					try {
						element.getAnnotation(RiseModule.class).presenter();
					} catch (MirroredTypeException presenter){
						childRiseModuleMetadata.setPresenterElement(types.asElement(presenter.getTypeMirror()));
					}

				}

				graph.put(childRiseModuleMetadata, new ArrayList<>());
			});
		}

		// links
		for(RiseModuleMetadata module: graph.keySet()){
			if(!module.isEntry()) {
				final RiseModuleMetadata parent = getParentModule(module);
				if(parent!=null){
					List<RiseModuleMetadata> parentChildren = graph.get(parent);
					parentChildren.add(module);
				}

			}
		}

		return graph;
	}

	public RiseModuleMetadata getParentModule(RiseModuleMetadata module) {
		final TypeMirror NO_PARENT = elements.getTypeElement(NoParent.class.getName()).asType();

		final RiseModule riseModuleAnnotation = module.getElement().getAnnotation(
			RiseModule.class
		);

		RiseModuleMetadata parent = null;

		try {
			riseModuleAnnotation.parent();
		} catch (MirroredTypeException e) {
			TypeMirror parentMirror = e.getTypeMirror();
			if (!types.isSameType(parentMirror, NO_PARENT)) {

				final Element parentElement = types.asElement(parentMirror);

				RiseModule ModuleAnnotation = parentElement.getAnnotation(RiseModule.class);
				ApplicationEntry ApplicationEntryAnnotation = parentElement.getAnnotation(ApplicationEntry.class);

				parent = new RiseModuleMetadata(parentElement);

				if (ModuleAnnotation != null) {
					try {
						ModuleAnnotation.view();
					} catch (MirroredTypeException view){
						parent.setViewElement(types.asElement(view.getTypeMirror()));

						try {
							ModuleAnnotation.presenter();
						} catch (MirroredTypeException presenter){
							parent.setPresenterElement(types.asElement(presenter.getTypeMirror()));
						}

					}
				} else if (ApplicationEntryAnnotation != null) {
					try {
						ApplicationEntryAnnotation.view();
					} catch (MirroredTypeException view){
						parent.setViewElement(types.asElement(view.getTypeMirror()));

						try {
							ApplicationEntryAnnotation.presenter();
						} catch (MirroredTypeException presenter){
							parent.setPresenterElement(types.asElement(presenter.getTypeMirror()));
						}

					}
				}
			}
		}

		return parent;
	}

}
