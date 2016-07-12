package org.vaadin.rise.codegen;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableList;
import org.vaadin.rise.codegen.step.ApplicationEntryProcessingStep;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.Processor;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Created by oem on 7/12/16.
 */
@AutoService(Processor.class)
public class RiseProcessor extends BasicAnnotationProcessor{

	@Override
	protected Iterable<? extends ProcessingStep> initSteps() {
		ImmutableList.Builder<? extends ProcessingStep> stepsBuilder = ImmutableList.builder();

		final Messager messager = processingEnv.getMessager();
		final Types types = processingEnv.getTypeUtils();
		final Elements elements = processingEnv.getElementUtils();
		final Filer filer = processingEnv.getFiler();


		return ImmutableList.of(
			new ApplicationEntryProcessingStep(messager, types, elements)
		);
	}


}
