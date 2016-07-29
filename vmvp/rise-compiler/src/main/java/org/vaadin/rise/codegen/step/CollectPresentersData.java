package org.vaadin.rise.codegen.step;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import org.vaadin.rise.codegen.model.FqnHolder;
import org.vaadin.rise.codegen.model.aNewOne.PresenterData;
import org.vaadin.rise.core.annotation.NoGateKeeper;
import org.vaadin.rise.core.annotation.Presenter;
import org.vaadin.rise.core.annotation.RiseModuleHelper;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

/**
 * Created by oem on 7/28/16.
 */
public class CollectPresentersData implements BasicAnnotationProcessor.ProcessingStep {

	private Elements elements;
	private Types types;
	private Map<FqnHolder, PresenterData> presenterDataList;
	private Set<String> places;

	public CollectPresentersData(Elements elements, Types types, Map<FqnHolder, PresenterData> presenterDataList, Set<String> places) {
		this.elements = elements;
		this.types = types;
		this.presenterDataList = presenterDataList;
		this.places = places;
	}

	@Override
	public Set<? extends Class<? extends Annotation>> annotations() {
		return ImmutableSet.of(Presenter.class);
	}

	@Override
	public Set<Element> process(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {

		for (Element presenterElement : elementsByAnnotation.get(Presenter.class)) {
			final Presenter presenterAnnotation = presenterElement.getAnnotation(Presenter.class);

			if (!presenterAnnotation.placeName().isEmpty()) {
				places.add(presenterAnnotation.placeName());
			}

			final PresenterData presenterData = new PresenterData(FqnHolder.buildJavaCompatibleFQN(presenterElement));

			presenterData.setErrorPlace(presenterAnnotation.errorPlace());
			presenterData.setAuthorizePlace(presenterAnnotation.authorizePlace());
			presenterData.setDefaultPlace(presenterAnnotation.defaultPlace());
			presenterData.setPlace(presenterAnnotation.placeName());
			presenterData.setPlaceBus(presenterAnnotation.placeBus());

			final TypeMirror NO_GATEKEEPER = elements.getTypeElement(NoGateKeeper.class.getName()).asType();
			final TypeMirror gateKeeperType = RiseModuleHelper.gateKeeperValue(presenterAnnotation);

			if (!types.isSameType(gateKeeperType, NO_GATEKEEPER)) {
				presenterData.setGateKeeper(FqnHolder.buildJavaCompatibleFQN(types.asElement(gateKeeperType)));
			}

			presenterDataList.put(presenterData.fqn, presenterData);
		}

		return ImmutableSet.of();
	}
}
