package org.vaadin.rise.codegen;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableList;
import freemarker.template.Configuration;
import org.vaadin.rise.codegen.freemaker.FQN;
import org.vaadin.rise.codegen.freemaker.NestedSlotModel;
import org.vaadin.rise.codegen.metadata.RiseModuleMetadata;
import org.vaadin.rise.codegen.step.ModulesProcessingStep;
import org.vaadin.rise.codegen.step.SlotProcessingStep;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.Processor;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oem on 7/12/16.
 */
@AutoService(Processor.class)
public class RiseProcessor extends BasicAnnotationProcessor {
	private final Map<RiseModuleMetadata, List<RiseModuleMetadata>> graph = new HashMap<>();
	private final Map<FQN, List<NestedSlotModel>> slotGraph = new HashMap<>();

	@Override
	protected Iterable<? extends ProcessingStep> initSteps() {
		ImmutableList.Builder<? extends ProcessingStep> stepsBuilder = ImmutableList.builder();

		final Messager messager = processingEnv.getMessager();
		final Types types = processingEnv.getTypeUtils();
		final Elements elements = processingEnv.getElementUtils();
		final Filer filer = processingEnv.getFiler();

		Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
		cfg.setClassForTemplateLoading(this.getClass(), "/template");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLogTemplateExceptions(false);




		return ImmutableList.of(
				new SlotProcessingStep(slotGraph, types, elements, filer, cfg),
				new ModulesProcessingStep(slotGraph, graph, types, elements, filer, cfg)
		);
	}
}
