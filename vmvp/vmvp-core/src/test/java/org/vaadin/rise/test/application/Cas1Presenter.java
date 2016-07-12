package org.vaadin.rise.test.application;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.proxy.annotation.StandardProxy;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/12/16.
 */
@StandardProxy
@Singleton
public class Cas1Presenter extends RisePresenterImpl implements Cas1.Presenter {



	@Inject
	public Cas1Presenter(Cas1View cas1View) {
		System.out.println("Cas1Presenter");
	}
}
