package org.vaadin.rise.codegen.step;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import com.sun.tools.javac.code.Symbol;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.vaadin.rise.codegen.freemaker.ElementsUtils;
import org.vaadin.rise.codegen.freemaker.FQN;
import org.vaadin.rise.codegen.freemaker.NestedSlotModel;
import org.vaadin.rise.proxy.annotation.ThisIsNestedSlot;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Aleksandr on 17.07.2016.
 */
public class SlotProcessingStep implements BasicAnnotationProcessor.ProcessingStep {

    private Map<FQN, List<NestedSlotModel>> slotGraph;
    private final Types types;
    private final Elements elements;
    private final Filer filer;
    private final Configuration cfg;

    public SlotProcessingStep(Map<FQN, List<NestedSlotModel>> slotGraph, Types types, Elements elements, Filer filer, Configuration cfg) {
        this.slotGraph = slotGraph;
        this.types = types;
        this.elements = elements;
        this.filer = filer;
        this.cfg = cfg;
    }



    @Override
    public Set<? extends Class<? extends Annotation>> annotations() {
        return ImmutableSet.of(ThisIsNestedSlot.class);
    }

    @Override
    public Set<Element> process(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {


        elementsByAnnotation.get(ThisIsNestedSlot.class).forEach(element -> {
            final NestedSlotModel nestedSlotModel =
                    new NestedSlotModel(
                            ElementsUtils.toFQN(elements, element),
                            ElementsUtils.toFQN(elements, Symbol.class.cast(element).owner)
                    );


            List<NestedSlotModel> slots = slotGraph.get(nestedSlotModel.getPresenterFQN());

            if (slots == null) {
                slots = new ArrayList<>();
                slotGraph.put(nestedSlotModel.getPresenterFQN(), slots);
            }

            slots.add(nestedSlotModel);

            try {
                final Template template = cfg.getTemplate("NestedSlot.ftl");

                JavaFileObject source = filer.createSourceFile(nestedSlotModel.getFQNClassName());

                try(Writer out = source.openWriter()) {
                    template.process(nestedSlotModel, out);
                } catch (TemplateException e) {
                    e.printStackTrace();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return ImmutableSet.of();
    }
}
