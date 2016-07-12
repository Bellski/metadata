package org.vaadin.rise.codegen.step;

import com.google.auto.common.BasicAnnotationProcessor.ProcessingStep;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import org.vaadin.rise.place.annotation.DefaultPlace;
import org.vaadin.rise.place.annotation.ErrorPlace;
import org.vaadin.rise.place.annotation.UnauthorizedPlace;

import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Created by oem on 7/12/16.
 */
public class PresenterProcessingStep implements ProcessingStep{
	@Override
	public Set<? extends Class<? extends Annotation>> annotations() {
		return ImmutableSet.of(
			DefaultPlace.class,
			ErrorPlace.class,
			UnauthorizedPlace.class
		);
	}

	@Override
	public Set<Element> process(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {
		return ImmutableSet.of();
	}
}
