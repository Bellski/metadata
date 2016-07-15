package generated.org.vaadin.rise.test.application;

import com.vaadin.ui.UI;
import generated.rise.Bootstrap;
import org.vaadin.rise.vaadin.VaadinModule;

/**
 * Created by oem on 7/15/16.
 */
public class Cas1Bootstrap {
	public static Bootstrap bootstrap(UI ui) {
		final Cas1Component app = DaggerCas1Component
			.builder()
			.vaadinModule(new VaadinModule(ui))
			.build();

		return app.bootstrap();
	}
}
