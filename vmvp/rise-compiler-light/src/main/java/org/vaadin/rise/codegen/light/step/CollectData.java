package org.vaadin.rise.codegen.light.step;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import com.sun.tools.javac.code.Symbol;
import org.vaadin.rise.codegen.light.model.*;
import org.vaadin.rise.codegen.light.utils.PresenterUtils;
import org.vaadin.rise.core.annotation.Presenter;
import org.vaadin.rise.error.BaseErrorManager;
import org.vaadin.rise.error.DefaultErrorManager;
import org.vaadin.rise.place.SecuredPresenterPlace;
import org.vaadin.rise.security.annotation.DefaultGateKeeper;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by oem on 8/5/16.
 */
public class CollectData implements BasicAnnotationProcessor.ProcessingStep{

	private final NameTokenPartsModel nameTokenPartsModel;
	private PlaceManagerModuleModel placeManagerModuleModel;
	private PlacesModuleModel placesModuleModel;
	private Elements elemetsUtil;

	public CollectData(NameTokenPartsModel nameTokenPartsModel, PlaceManagerModuleModel placeManagerModuleModel, PlacesModuleModel placesModuleModel, Elements elemetsUtil) {
		this.nameTokenPartsModel = nameTokenPartsModel;
		this.placeManagerModuleModel = placeManagerModuleModel;
		this.placesModuleModel = placesModuleModel;
		this.elemetsUtil = elemetsUtil;
	}

	@Override
	public Set<? extends Class<? extends Annotation>> annotations() {
		return ImmutableSet.of(Presenter.class, DefaultErrorManager.class);
	}

	@Override
	public Set<Element> process(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {
		final Set<Element> presenters = elementsByAnnotation.get(Presenter.class);
		final Set<Element> defaultErrorManagers = elementsByAnnotation.get(DefaultErrorManager.class);
		final Set<Element> defaultGateKeeper = elementsByAnnotation.get(DefaultGateKeeper.class);

		final Set<String> packages = new HashSet<>();

		for (Element presenter : presenters) {
			final String packageName = Symbol.class.cast(presenter).packge().getQualifiedName().toString();
			packages.add(packageName);
		}

		final String rootPackage = findRoot(packages);

		nameTokenPartsModel.setPackageName(rootPackage);
		placeManagerModuleModel.setPackageName(rootPackage);
		placesModuleModel.setPackageName(rootPackage);


		collectDataForPlaceManager(presenters, defaultErrorManagers, defaultGateKeeper.size() == 1 ? defaultGateKeeper.iterator().next() : null);
		collectPlaces(presenters);

		return ImmutableSet.of();
	}

	private void collectDataForPlaceManager(Set<Element> presenters, Set<Element> defaultErrorManagers, Element defaultGateKeeper) {
		final Element errorManager;

		if (defaultErrorManagers.size() == 1) {
			errorManager = defaultErrorManagers.iterator().next();
		} else {
			errorManager = elemetsUtil.getTypeElement(BaseErrorManager.class.getName());
		}

		placeManagerModuleModel.setErrorManager(FqnHolder.buildJavaCompatibleFQN(errorManager));

		for (Element presenter : presenters) {
			final Presenter presenterAnnotation = presenter.getAnnotation(Presenter.class);

			final String placeName = presenterAnnotation.placeName();

			if (!placeName.isEmpty()) {
				final String presenterName = presenter.getSimpleName().toString();
				final String presenterPackageName = Symbol.class.cast(presenter).packge().getQualifiedName().toString();

				final Symbol.TypeSymbol useGateKeeper = PresenterUtils.getGateKeeper(presenterAnnotation);

				final PlacePresenterModel placePresenterModel = new PlacePresenterModel(presenterName, presenterPackageName);
				placePresenterModel.setPlaceName(placeName);

				if (defaultGateKeeper != null && useGateKeeper == null) {
					placePresenterModel.setPlaceImplementation(
						FqnHolder.buildJavaCompatibleFQN(elemetsUtil.getTypeElement(SecuredPresenterPlace.class.getName()))
					);

					placePresenterModel.setGateKeeper(FqnHolder.buildJavaCompatibleFQN(defaultGateKeeper));
				} else if (useGateKeeper != null) {
					placePresenterModel.setPlaceImplementation(
						FqnHolder.buildJavaCompatibleFQN(elemetsUtil.getTypeElement(SecuredPresenterPlace.class.getName()))
					);

					placePresenterModel.setGateKeeper(FqnHolder.buildJavaCompatibleFQN(useGateKeeper));
				}

				placesModuleModel.addPresenterModel(placePresenterModel);

				if (presenterAnnotation.defaultPlace()) {
					placeManagerModuleModel.setDefaultPlace(placeName);
				}

				if (presenterAnnotation.errorPlace()) {
					placeManagerModuleModel.setErrorPlace(placeName);
				}

				if (presenterAnnotation.authorizePlace()) {
					placeManagerModuleModel.setUnauthorizedPlace(placeName);
				}
			}


		}

	}

	private void collectPlaces(Set<Element> elements) {
		final Set<String> namePlaces = new HashSet<>();

		for (Element element : elements) {
			final Presenter presenterAnnotation = element.getAnnotation(Presenter.class);
			namePlaces.add(presenterAnnotation.placeName());
		}

		nameTokenPartsModel.addPlaces(namePlaces);
	}

	public static String findRoot(Set<String> paths){
		if(paths==null || paths.size()==0){
			return null;
		}

		List<String> roots = new ArrayList<>();
		for(String path: paths){
			if(path==null){
				continue;
			}

			String newRoot = path;
			for (Iterator<String> iterator = roots.iterator(); iterator.hasNext(); ) {
				String root = iterator.next();
				if (path.startsWith(root)) { // root found
					newRoot = null;
					break;
				}
				if (root.startsWith(path)) { // path is root for old root
					newRoot = path;
					iterator.remove();
				}
			}
			if(newRoot != null){
				roots.add(newRoot);
			}
		}

		if(roots.size()==0){
			return null;
		}
		if(roots.size()>1) {
			throw new IllegalArgumentException("Multiple roots found: "+roots);
		}

		return roots.get(0);
	}
}
