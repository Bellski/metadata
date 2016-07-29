package org.vaadin.rise.codegen.step;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import com.sun.tools.javac.code.Symbol;
import org.vaadin.rise.annotation.ApplicationEntry;
import org.vaadin.rise.codegen.generator.PlaceManagerModuleGenerator;
import org.vaadin.rise.codegen.helpers.PackageNameHolder;
import org.vaadin.rise.codegen.model.FqnHolder;
import org.vaadin.rise.codegen.model.PlaceManagerModuleModel;
import org.vaadin.rise.core.annotation.Presenter;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Aleksandr on 17.07.2016.
 */
public class PlaceProcessingStep implements BasicAnnotationProcessor.ProcessingStep {
    private PackageNameHolder packageEntry;
    private final Map<String, FqnHolder> presenterByPlace;
    private final Set<String> places;
    private final PlaceManagerModuleGenerator placeManagerModuleGenerator;
    private List<JavaFileObject> jfosForDaggerGeneration;
    private final Types types;
    private final Elements elements;

    public PlaceProcessingStep(PackageNameHolder packageEntry,
                               Map<String, FqnHolder> presenterByPlace,
                               Set<String> places,
                               PlaceManagerModuleGenerator placeManagerModuleGenerator,
                               List<JavaFileObject> jfosForDaggerGeneration,
                               Types types,
                               Elements elements) {

        this.packageEntry = packageEntry;
        this.presenterByPlace = presenterByPlace;
        this.places = places;
        this.placeManagerModuleGenerator = placeManagerModuleGenerator;
        this.jfosForDaggerGeneration = jfosForDaggerGeneration;
        this.types = types;
        this.elements = elements;
    }

    @Override
    public Set<? extends Class<? extends Annotation>> annotations() {
        return ImmutableSet.of(Presenter.class, ApplicationEntry.class);
    }

    @Override
    public Set<Element> process(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {
        final Set<Element> presenterElements = elementsByAnnotation.get(Presenter.class);

        final PlaceManagerModuleModel placeManagerModuleModel = new PlaceManagerModuleModel(packageEntry.getPackageName());

        final Set<Element> applicationEntries = elementsByAnnotation.get(ApplicationEntry.class);

        if (applicationEntries != null) {
            final ApplicationEntry appAnnotation = applicationEntries
                .iterator()
                .next()
                .getAnnotation(ApplicationEntry.class);
        }

        if (presenterElements != null) {
            for (Element presenterElement : presenterElements) {
                final Presenter presenterAnnotation = presenterElement.getAnnotation(Presenter.class);

                final String presenterPackage = Symbol.class.cast(presenterElement).packge().getQualifiedName().toString();
                final String presenterName = presenterElement.getSimpleName().toString();


            }
        }



        return ImmutableSet.of();
    }
}
