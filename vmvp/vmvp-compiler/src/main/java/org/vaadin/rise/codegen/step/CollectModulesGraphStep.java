package org.vaadin.rise.codegen.step;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import dagger.Module;
import org.vaadin.rise.annotation.ApplicationEntry;
import org.vaadin.rise.codegen.metadata.RiseModuleMetadata;
import org.vaadin.rise.core.annotation.NoParent;
import org.vaadin.rise.core.annotation.RiseModule;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by oem on 7/15/16.
 */
public class CollectModulesGraphStep implements BasicAnnotationProcessor.ProcessingStep {

	private Map<RiseModuleMetadata, List<RiseModuleMetadata>> graph;
	private final Types types;
	private final Elements elements;
	private final Filer filer;

	public CollectModulesGraphStep(Map<RiseModuleMetadata, List<RiseModuleMetadata>> graph, Types types, Elements elements, Filer filer) {
		this.graph = graph;
		this.types = types;
		this.elements = elements;
		this.filer = filer;
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
		final Map<RiseModuleMetadata, List<RiseModuleMetadata>> graph = new HashMap<>();

		if (elementsByAnnotation.containsKey(ApplicationEntry.class)) {
			final Element element = elementsByAnnotation.get(ApplicationEntry.class).iterator().next();
			graph.put(
				 new RiseModuleMetadata(element, true)
				,new ArrayList<>()
			);
		}

		if (elementsByAnnotation.containsKey(RiseModule.class)) {
			elementsByAnnotation.get(RiseModule.class).forEach(element -> {
				graph.put(
					new RiseModuleMetadata(element)
					, new ArrayList<>()
				);
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

		writeModule(graph);

		return ImmutableSet.of();
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
				parent = new RiseModuleMetadata(types.asElement(parentMirror));
			}
		}

		return parent;
	}

	private void writeModule(Map<RiseModuleMetadata, List<RiseModuleMetadata>> graph) {
		graph.forEach((module, children) -> {
			final AnnotationSpec.Builder moduleAnnotationSpec = AnnotationSpec.builder(Module.class);

			children.forEach(riseModuleMetadata -> moduleAnnotationSpec.addMember("includes", "$T.$L", riseModuleMetadata.getElement(), "class"));

			final TypeSpec.Builder riseTypeSpec = createRiseTypeSpec(module.getElement());
			riseTypeSpec.addAnnotation(moduleAnnotationSpec.build());


			try {
				final JavaFile javaFIle = createJavaFile(
					elements.getPackageOf(module.getElement()).getSimpleName().toString(),
					riseTypeSpec
				);

				System.out.println(javaFIle);

				javaFIle.writeTo(filer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}



	private TypeSpec.Builder createRiseTypeSpec(Element element) {
		return TypeSpec
			.classBuilder("Rise" + element.getSimpleName())
			.addModifiers(Modifier.PUBLIC);
	}

	private JavaFile createJavaFile(String packageName, TypeSpec.Builder body) {
		return JavaFile.builder(packageName, body.build()).build();
	}
}
