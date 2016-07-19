package javasource;

import dagger.Component;
import org.vaadin.rise.vaadin.VaadinModule;

import javasource.RiseBootstrapModule;
import javasource.RiseEntryModule;
import javasource.Bootstrap;


import javax.inject.Singleton;


@Singleton
@Component(modules = {RiseBootstrapModule.class, RiseEntryModule.class})
public interface RiseEntryModuleComponent {
	Bootstrap bootstrap();
}