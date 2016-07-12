package org.vaadin.rise.test.application.generated.org.vaadin.rise.test.application;

import dagger.Component;
import org.vaadin.rise.test.application.generated.Bootstrap;
import org.vaadin.rise.vaadin.VaadinModule;

import javax.inject.Singleton;

/**
 * Created by oem on 7/12/16.
 */
@Singleton
@Component(modules = {RiseCas1Application.class, VaadinModule.class})
public interface Cas1Component {
	Bootstrap bootstrap();
}
