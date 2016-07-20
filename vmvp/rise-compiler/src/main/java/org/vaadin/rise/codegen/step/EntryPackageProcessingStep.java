package org.vaadin.rise.codegen.step;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import com.sun.tools.javac.code.Symbol;
import org.vaadin.rise.annotation.ApplicationEntry;

import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Created by oem on 7/19/16.
 */
public class EntryPackageProcessingStep implements BasicAnnotationProcessor.ProcessingStep {
	private EntryPackageProcessingStepCallBack callBack;

	public EntryPackageProcessingStep(EntryPackageProcessingStepCallBack callBack) {
		this.callBack = callBack;
	}

	@Override
	public Set<? extends Class<? extends Annotation>> annotations() {
		return ImmutableSet.of(ApplicationEntry.class);
	}

	@Override
	public Set<Element> process(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {
		final Element applicationEntryElement = elementsByAnnotation.get(ApplicationEntry.class).iterator().next();

		callBack.onEntryPackageProcessingStepEnd(Symbol.class.cast(applicationEntryElement).packge().toString());

		return ImmutableSet.of();
	}
}
