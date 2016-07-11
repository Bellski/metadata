package ru.bellski.vmvp;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import ru.bellski.vmvp.application.Application;
import ru.bellski.vmvp.application.ApplicationBootstrapModule;
import ru.bellski.vmvp.application.DaggerApplication;
import ru.bellski.vmvp.application.home.HomeModule;
import ru.bellski.vmvp.mvp.root.DaggerRoot;
import ru.bellski.vmvp.mvp.root.Root;
import ru.bellski.vmvp.mvp.root.RootModule;


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
        Application daggerApp = DaggerApplication
            .builder()
            .vaadinModule(new VaadinModule(this))
            .applicationBootstrapModule(new ApplicationBootstrapModule())
            .rootModule(new RootModule())
            .homeModule(new HomeModule())
            .build();

        daggerApp.instantinateProxies();
        daggerApp.bootstrap();
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

    }
}
