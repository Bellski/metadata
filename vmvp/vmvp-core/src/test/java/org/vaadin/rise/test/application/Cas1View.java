package org.vaadin.rise.test.application;

import com.vaadin.ui.UI;
import org.vaadin.rise.core.RiseViewImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/12/16.
 */
@Singleton
public class Cas1View extends RiseViewImpl implements Cas1.View {

	@Inject
	public Cas1View(UI ui) {
		System.out.println("Cas1View");
	}
}
