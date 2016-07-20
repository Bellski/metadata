package javasource;

import dagger.Component;
import javasource.Bootstrap;

import javax.inject.Singleton;


@Singleton
@Component(modules = {RiseBootstrapModule.class, RiseEntryModule.class})
public interface RiseEntryModuleComponent {
	Bootstrap bootstrap();
}