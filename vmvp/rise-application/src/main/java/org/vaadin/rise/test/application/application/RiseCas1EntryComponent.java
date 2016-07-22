
package org.vaadin.rise.test.application.application;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {RiseBootstrapModule.class, RiseCas1Entry.class})
public interface RiseCas1EntryComponent {
    Bootstrap bootstrap();
}