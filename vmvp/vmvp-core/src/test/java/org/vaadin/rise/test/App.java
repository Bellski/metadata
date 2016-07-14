package org.vaadin.rise.test;


import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import generated.RiseBootstrapModule;
import generated.org.vaadin.rise.test.application.Cas1Component;
import generated.org.vaadin.rise.test.application.DaggerCas1Component;
import generated.org.vaadin.rise.test.application.RiseCas1Application;
import generated.org.vaadin.rise.test.application.claimlist.RiseClaimListModule;
import org.vaadin.rise.place.PlaceManagerModule;
import org.vaadin.rise.vaadin.VaadinModule;

/**
 * Created by oem on 7/12/16.
 */
public class App {
	public static void main(String[] args) {
		final Cas1Component app = DaggerCas1Component
				.builder()
				.riseBootstrapModule(new RiseBootstrapModule())
				.riseClaimListModule(new RiseClaimListModule())
				.placeManagerModule(new PlaceManagerModule("!claimlist", "!claimlist", "!claimlist"))
				.vaadinModule(new VaadinModule(new UI() {
					@Override
					protected void init(VaadinRequest request) {

					}
				}))
				.riseCas1Application(new RiseCas1Application())
				.build();

		app.bootstrap();
	}
}
