package org.vaadin.rise.codegen.step;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import org.vaadin.rise.core.annotation.Presenter;

import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Created by Aleksandr on 17.07.2016.
 */
public class ProxyProcessingStep implements BasicAnnotationProcessor.ProcessingStep {
    @Override
    public Set<? extends Class<? extends Annotation>> annotations() {
        return ImmutableSet.of(Presenter.class);
    }

    @Override
    public Set<Element> process(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {


        return ImmutableSet.of();
    }
}
