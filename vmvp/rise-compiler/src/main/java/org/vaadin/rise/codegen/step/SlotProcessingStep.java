package org.vaadin.rise.codegen.step;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import com.sun.tools.javac.code.Symbol;
import freemarker.template.TemplateException;
import org.vaadin.rise.codegen.generator.NestedSlotGenerator;
import org.vaadin.rise.codegen.model.FqnHolder;
import org.vaadin.rise.codegen.model.NestedSlotModel;
import org.vaadin.rise.slot.annotation.ThisIsNestedSlot;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Aleksandr on 17.07.2016.
 */
public class SlotProcessingStep implements BasicAnnotationProcessor.ProcessingStep {

    private Map<FqnHolder, List<NestedSlotModel>> slotGraph;
    private final NestedSlotGenerator nestedSlotGenerator;
    private List<JavaFileObject> jfosForDaggerGeneration;
    private final Types types;
    private final Elements elements;


    public SlotProcessingStep(Map<FqnHolder, List<NestedSlotModel>> slotGraph, NestedSlotGenerator nestedSlotGenerator, List<JavaFileObject> jfosForDaggerGeneration, Types types, Elements elements) {
        this.slotGraph = slotGraph;
        this.nestedSlotGenerator = nestedSlotGenerator;
        this.jfosForDaggerGeneration = jfosForDaggerGeneration;
        this.types = types;
        this.elements = elements;
    }


    @Override
    public Set<? extends Class<? extends Annotation>> annotations() {
        return ImmutableSet.of(ThisIsNestedSlot.class);
    }

    @Override
    public Set<Element> process(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {
        final List<NestedSlotModel> slotModels = new ArrayList<>();

        for (Element element : elementsByAnnotation.get(ThisIsNestedSlot.class)) {
            final Symbol symbolSlot = Symbol.class.cast(element);

            final String packageName = symbolSlot.packge().getQualifiedName().toString();
            final String slotFqn = symbolSlot.getQualifiedName().toString();

            final NestedSlotModel nestedSlotModel = new NestedSlotModel
                (
                    slotFqn.substring(packageName.length() + 1).replaceAll("\\.", ""),
                    packageName,
                    FqnHolder.buildJavaCompatibleFQN(element)
                );

            nestedSlotModel.setSlotOwner(FqnHolder.buildJavaCompatibleFQN(symbolSlot.owner));

            List<NestedSlotModel> slots = slotGraph.get(nestedSlotModel.getSlotOwner());

            if (slots == null) {
                slots = new ArrayList<>();
                slotGraph.put(nestedSlotModel.getSlotOwner(), slots);
            }

            slots.add(nestedSlotModel);
            slotModels.add(nestedSlotModel);
        }

        try {
            jfosForDaggerGeneration.addAll(nestedSlotGenerator.generate(slotModels));
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }


        return ImmutableSet.of();
    }
}
