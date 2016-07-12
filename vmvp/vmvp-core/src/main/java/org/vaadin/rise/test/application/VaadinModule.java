package org.vaadin.rise.test.application;

import com.vaadin.server.Page;
import com.vaadin.ui.UI;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by oem on 7/11/16.
 */
@Module
public class VaadinModule {
	private final UI ui;

	public VaadinModule(UI ui) {
		this.ui = ui;
	}

	@Singleton
	@Provides
	public Page providesPage() {
		return ui.getPage();
	}

	@Singleton
	@Provides
	UI providesUi() {
		return ui;
	}
}
