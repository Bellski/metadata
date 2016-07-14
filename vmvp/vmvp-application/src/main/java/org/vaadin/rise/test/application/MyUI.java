package org.vaadin.rise.test.application;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import generated.RiseBootstrapModule;
import generated.org.vaadin.rise.test.application.Cas1Component;
import generated.org.vaadin.rise.test.application.DaggerCas1Component;
import generated.org.vaadin.rise.test.application.RiseCas1Application;
import generated.org.vaadin.rise.test.application.claimlist.RiseClaimListModule;
import org.vaadin.rise.place.PlaceManagerModule;
import org.vaadin.rise.vaadin.VaadinModule;

import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("valo")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final Cas1Component app = DaggerCas1Component
            .builder()
            .riseBootstrapModule(new RiseBootstrapModule())
            .riseClaimListModule(new RiseClaimListModule())
            .placeManagerModule(new PlaceManagerModule("!claimlist", "!error", "!claimlist"))
            .vaadinModule(new VaadinModule(this))
            .riseCas1Application(new RiseCas1Application())
            .build();

        app.bootstrap();
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

    }
}
