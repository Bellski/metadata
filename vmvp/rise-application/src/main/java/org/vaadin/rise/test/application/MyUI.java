package org.vaadin.rise.test.application;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import dagger.Component;
import org.vaadin.rise.place.api.PlaceManager;
import org.vaadin.rise.test.application.application.ApplicationModule;
import org.vaadin.rise.vaadin.VaadinModule;

import javax.inject.Singleton;
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


    @Singleton
    @Component(modules = ApplicationModule.class)
    public interface ApplicationComponent {
        PlaceManager PLACE_MANAGER();
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        DaggerMyUI_ApplicationComponent
                .builder()
                .vaadinModule(new VaadinModule(this))
                .build()
                .PLACE_MANAGER().revealCurrentPlace();
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

    }
}
