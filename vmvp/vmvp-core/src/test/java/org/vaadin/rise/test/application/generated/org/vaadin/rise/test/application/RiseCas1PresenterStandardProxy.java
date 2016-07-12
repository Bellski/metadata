package org.vaadin.rise.test.application.generated.org.vaadin.rise.test.application;

import dagger.Lazy;
import org.vaadin.rise.test.application.Cas1Presenter;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/12/16.
 */
@Singleton
public class RiseCas1PresenterStandardProxy {
	private final Lazy<Cas1Presenter> cas1Presenter;



	@Inject
	public RiseCas1PresenterStandardProxy(Lazy<Cas1Presenter> cas1Presenter) {
		System.out.println("RiseCas1PresenterStandardProxy ");

		this.cas1Presenter = cas1Presenter;
	}
}
