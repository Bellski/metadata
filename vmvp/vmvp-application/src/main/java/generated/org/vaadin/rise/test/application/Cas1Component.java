package generated.org.vaadin.rise.test.application;

import dagger.Component;
import generated.Bootstrap;
import generated.RiseBootstrapModule;
import generated.org.vaadin.rise.test.application.claiminfo.RiseClaimInfoModule;
import generated.org.vaadin.rise.test.application.claimlist.RiseClaimListModule;
import generated.org.vaadin.rise.test.application.error.RiseErrorModule;
import org.vaadin.rise.place.PlaceManagerModule;
import org.vaadin.rise.test.application.application.error.ErrorModule;
import org.vaadin.rise.vaadin.VaadinModule;

import javax.inject.Singleton;

/**
 * Created by oem on 7/12/16.
 */
@Singleton
@Component(modules = {RiseCas1Application.class, RiseBootstrapModule.class, VaadinModule.class, RiseClaimListModule.class, RiseClaimInfoModule.class, RiseErrorModule.class, PlaceManagerModule.class})
public interface Cas1Component {
	Bootstrap bootstrap();
}
