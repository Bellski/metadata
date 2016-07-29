package org.vaadin.rise.codegen;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableList;
import freemarker.template.Configuration;
import org.vaadin.rise.codegen.generator.ModuleGenerator;
import org.vaadin.rise.codegen.generator.NameTokensGenerator;
import org.vaadin.rise.codegen.generator.NestedSlotGenerator;
import org.vaadin.rise.codegen.generator.PlaceManagerModuleGenerator;
import org.vaadin.rise.codegen.helpers.PackageNameHolder;
import org.vaadin.rise.codegen.model.FqnHolder;
import org.vaadin.rise.codegen.model.NestedSlotModel;
import org.vaadin.rise.codegen.model.aNewOne.PresenterData;
import org.vaadin.rise.codegen.step.*;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.Processor;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;
import java.util.*;

/**
 * Created by oem on 7/12/16.
 */
@AutoService(Processor.class)
public class RiseProcessor extends BasicAnnotationProcessor implements EntryPackageProcessingStepCallBack, DaggerJFOSGenerateCallBack {
	private final Map<FqnHolder, List<NestedSlotModel>> slotGraph = new HashMap<>();
	private final Map<String, FqnHolder> presenterByPlace = new HashMap<>();
	private final Set<String> places = new HashSet<>();

	private PackageNameHolder packageEntry = new PackageNameHolder();
	private List<JavaFileObject> jfosForDaggerGeneration = new ArrayList<>();

	private Map<FqnHolder, PresenterData> presenterDatas = new HashMap<>();


	@Override
	protected Iterable<? extends ProcessingStep> initSteps() {
		final Messager messager = processingEnv.getMessager();
		final Types types = processingEnv.getTypeUtils();
		final Elements elements = processingEnv.getElementUtils();
		final Filer filer = processingEnv.getFiler();

		Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
		cfg.setClassForTemplateLoading(this.getClass(), "/template");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLogTemplateExceptions(false);


		final NestedSlotGenerator nestedSlotGenerator = new NestedSlotGenerator(cfg, filer);
		final PlaceManagerModuleGenerator placeManagerModuleGenerator = new PlaceManagerModuleGenerator(cfg, filer);
		final ModuleGenerator moduleGenerator = new ModuleGenerator(cfg, filer);
		final NameTokensGenerator nameTokensGenerator = new NameTokensGenerator(cfg, filer);


		return ImmutableList.of(

				new CollectPresentersData(elements, types, presenterDatas, places),
				new SlotProcessingStep(slotGraph, nestedSlotGenerator, jfosForDaggerGeneration, types, elements),
				new RiseModuleProcessingStep(slotGraph, moduleGenerator, places, nameTokensGenerator, jfosForDaggerGeneration, types, elements),
				new ModulesProcessingStep(
					slotGraph,
					jfosForDaggerGeneration,
					nameTokensGenerator,
					placeManagerModuleGenerator,
					places,
					types,
					elements,
					moduleGenerator,
					jfosForDaggerGeneration,
					presenterDatas
				)
		);
	}

	@Override
	public void onEntryPackageProcessingStepEnd(String packageName) {
		packageEntry.setPackageName(packageName);
	}

	@Override
	public void onJFOSGenerate(List<JavaFileObject> jfos) {
		jfosForDaggerGeneration.addAll(jfos);
	}
}
