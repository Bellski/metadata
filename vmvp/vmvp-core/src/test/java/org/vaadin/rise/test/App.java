package org.vaadin.rise.test;


import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.vaadin.rise.test.application.generated.org.vaadin.rise.test.application.Cas1Component;
import org.vaadin.rise.test.application.generated.org.vaadin.rise.test.application.DaggerCas1Component;
import org.vaadin.rise.vaadin.VaadinModule;

/**
 * Created by oem on 7/12/16.
 */
public class App {
	public static void main(String[] args) {
		final Cas1Component app = DaggerCas1Component
			.builder()
			.vaadinModule(new VaadinModule(new UI() {
				@Override
				protected void init(VaadinRequest request) {

				}
			}))
			.build();

		app.bootstrap();
		app.bootstrap();
	}
}
