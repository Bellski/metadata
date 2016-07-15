package generated.org.vaadin.rise.test.application;

import dagger.Component;
import generated.rise.Bootstrap;
import generated.rise.RiseBootstrapModule;

import javax.inject.Singleton;

/**
 * Created by oem on 7/12/16.
 */
@Singleton
@Component(modules = {RiseBootstrapModule.class, RiseCas1Entry.class})
public interface Cas1Component {
	Bootstrap bootstrap();
}
