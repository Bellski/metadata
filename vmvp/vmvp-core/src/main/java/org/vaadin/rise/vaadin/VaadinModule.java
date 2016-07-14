package org.vaadin.rise.vaadin;

import com.vaadin.server.Page;
import com.vaadin.ui.UI;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by oem on 7/12/16.
 */
@Module
public class VaadinModule {

	private final UI ui;

	public VaadinModule(UI ui) {
		this.ui = ui;
	}

	@Singleton @Provides
	UI providesUI() {
		return ui;
	}

	@Singleton @Provides
	Page providesPage() {
		return ui.getPage();
	}
}
