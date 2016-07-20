package org.vaadin.rise.codegen.step;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import com.sun.tools.javac.code.Symbol;
import freemarker.template.TemplateException;
import org.vaadin.rise.codegen.generator.*;
import org.vaadin.rise.codegen.helpers.PackageNameHolder;
import org.vaadin.rise.codegen.model.*;
import org.vaadin.rise.core.annotation.Presenter;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;

import static java.util.Collections.singletonList;

/**
 * Created by Aleksandr on 17.07.2016.
 */
public class ProxyProcessingStep implements BasicAnnotationProcessor.ProcessingStep {
    private PackageNameHolder packageEntry;
    private final Map<FqnHolder, ProxyModel> proxyModels;
    private final Set<String> places;
    private final ProxyGenerator proxyGenerator;
    private final PlaceManagerModuleGenerator placeManagerModuleGenerator;
    private EagerProxiesGenerator eagerProxiesGenerator;
    private BootstrapGenerator bootstrapGenerator;
    private RiseBootstrapModuleGenerator riseBootstrapModuleGenerator;
    private List<JavaFileObject> jfosForDaggerGeneration;
    private final Types types;
    private final Elements elements;

    public ProxyProcessingStep(PackageNameHolder packageEntry,
                               Map<FqnHolder, ProxyModel> proxyModels,
                               Set<String> places,
                               ProxyGenerator proxyGenerator,
                               PlaceManagerModuleGenerator placeManagerModuleGenerator,
                               EagerProxiesGenerator eagerProxiesGenerator,
                               BootstrapGenerator bootstrapGenerator,
                               RiseBootstrapModuleGenerator riseBootstrapModuleGenerator,
                               List<JavaFileObject> jfosForDaggerGeneration,
                               Types types,
                               Elements elements) {

        this.packageEntry = packageEntry;
        this.proxyModels = proxyModels;
        this.places = places;
        this.proxyGenerator = proxyGenerator;
        this.placeManagerModuleGenerator = placeManagerModuleGenerator;
        this.eagerProxiesGenerator = eagerProxiesGenerator;
        this.bootstrapGenerator = bootstrapGenerator;
        this.riseBootstrapModuleGenerator = riseBootstrapModuleGenerator;
        this.jfosForDaggerGeneration = jfosForDaggerGeneration;
        this.types = types;
        this.elements = elements;
    }

    @Override
    public Set<? extends Class<? extends Annotation>> annotations() {
        return ImmutableSet.of(Presenter.class);
    }

    @Override
    public Set<Element> process(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {
        final Set<Element> presenterElements = elementsByAnnotation.get(Presenter.class);

        final PlaceManagerModuleModel placeManagerModuleModel = new PlaceManagerModuleModel(packageEntry.getPackageName());

        List<ProxyModel> proxyModelList = new ArrayList<>();

        if (presenterElements != null) {
            for (Element presenterElement : presenterElements) {
                final Presenter presenterAnnotation = presenterElement.getAnnotation(Presenter.class);

                final String presenterPackage = Symbol.class.cast(presenterElement).packge().getQualifiedName().toString();
                final String presenterName = presenterElement.getSimpleName().toString();

                final ProxyModel proxyModel = new ProxyModel
                    (
                        presenterName,
                        presenterPackage,
                        FqnHolder.buildJavaCompatibleFQN(presenterElement)
                    );

                String placeName = presenterAnnotation.placeName();

                if (!placeName.isEmpty()) {
                    proxyModel.setPlaceName(placeName);
                    places.add(placeName);
                }

                if (presenterAnnotation.defaultPlace()) {
                    placeManagerModuleModel.setDefaultPlaceNameToken(placeName);
                }

                if (presenterAnnotation.errorPlace()) {
                    placeManagerModuleModel.setErrorPlaceNameToken(placeName);
                }

                if (presenterAnnotation.authorizePlace()) {
                    placeManagerModuleModel.setUnauthorizedPlaceNameToken(placeName);
                }

                proxyModelList.add(proxyModel);
                proxyModels.put(FqnHolder.buildJavaCompatibleFQN(presenterElement), proxyModel);
            }
        }


        try {
            jfosForDaggerGeneration.addAll(
                proxyGenerator.generate(proxyModelList)
            );

            jfosForDaggerGeneration.addAll(
                placeManagerModuleGenerator.generate(
                    singletonList(placeManagerModuleModel)
                )
            );

            final EagerProxiesModel eagerProxyModel = new EagerProxiesModel(
                proxyModelList,
                packageEntry.getPackageName()
            );

            jfosForDaggerGeneration.addAll(
                eagerProxiesGenerator.generate(
                    singletonList(
                        eagerProxyModel
                    )
                )
            );

            jfosForDaggerGeneration.addAll(
                riseBootstrapModuleGenerator.generate(
                    singletonList(
                        new RiseBootstrapModuleModel(
                            places,
                            packageEntry.getPackageName()
                        )
                    )
                )
            );

            jfosForDaggerGeneration.addAll(
                bootstrapGenerator.generate(
                    singletonList(
                        new BootstrapModel(eagerProxyModel)
                    )
                )
            );

        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

        return ImmutableSet.of();
    }
}
