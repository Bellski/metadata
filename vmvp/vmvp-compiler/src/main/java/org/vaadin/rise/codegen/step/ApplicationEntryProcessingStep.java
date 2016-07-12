package org.vaadin.rise.codegen.step;

import com.google.auto.common.BasicAnnotationProcessor.ProcessingStep;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import org.vaadin.rise.annotation.ApplicationEntry;
import org.vaadin.rise.core.RisePresenterImpl;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Created by oem on 7/12/16.
 */
public class ApplicationEntryProcessingStep implements ProcessingStep {
	private final Messager messager;
	private final Types type;
	private final Elements elements;

	public ApplicationEntryProcessingStep(Messager messager, Types types, Elements elements) {
		this.messager = messager;
		this.type = types;
		this.elements = elements;
	}

	@Override
	public Set<? extends Class<? extends Annotation>> annotations() {
		return ImmutableSet.of(ApplicationEntry.class);
	}

	@Override
	public Set<Element> process(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {
		ImmutableSet.Builder<Element> rejectedElements = ImmutableSet.builder();

		final Set<Element> applicationEntries = elementsByAnnotation.get(ApplicationEntry.class);

		if (applicationEntries.size() > 0 && foundOnlyOneEntry(applicationEntries)) {
			final Element applicationEntryElement = applicationEntries.iterator().next();

			if (validatePresenter(applicationEntryElement.getAnnotation(ApplicationEntry.class))) {
				generate();
			}
		}

		return rejectedElements.build();
	}


	private boolean validatePresenter(ApplicationEntry appEntryAnnotation) {
		boolean isValid = true;

		TypeMirror presenterMirrorType = null;

		try {
			appEntryAnnotation.presenter();
		} catch (MirroredTypeException e) {
			presenterMirrorType = e.getTypeMirror();
		}

		if (!type.isSubtype(presenterMirrorType, elements.getTypeElement(RisePresenterImpl.class.getName()).asType())) {
			isValid = false;
			messager.printMessage(Diagnostic.Kind.ERROR, "Презентер на который ссылается ApplicationEntry должен наследовать RisePresenterImpl");
		}

		return isValid;
	}

	private boolean foundOnlyOneEntry(Set<Element> applicationEntries) {
		boolean applicationEntryIsValid = true;

		if (applicationEntries.size() > 1) {
			applicationEntryIsValid = false;
			messager.printMessage(Diagnostic.Kind.ERROR, "Больше одного ApplicationEntry");
		}

		return applicationEntryIsValid;
	}

	private void generate() {

	}
}
