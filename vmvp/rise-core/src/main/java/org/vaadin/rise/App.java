package org.vaadin.rise;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;
import dagger.multibindings.IntoSet;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.Set;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public class App {
    @Module
    public static class BusModule {
        @Provides @Singleton @ElementsIntoSet @Places1 Set<String> places() {
            return Collections.emptySet();
        }

        @Provides @Singleton @ElementsIntoSet @Places2 Set<String> places2() {
            return Collections.emptySet();
        }
    }

    @Module
    public static class Child1 {
        @Provides @Singleton @IntoSet @Places1 String value1() {
            return "child1";
        }

        @Provides @Singleton @IntoSet @Places2 String value2() {
            return "child2";
        }
    }


    @Singleton
    @Component(modules = {BusModule.class, Child1.class})
    public interface BusComponent {
        @Places1 Set<String> places1();
        @Places2 Set<String> places2();
    }

    public static void main(String[] args) {
        System.out.println(DaggerApp_BusComponent.create().places1());
        System.out.println(DaggerApp_BusComponent.create().places2());
    }
}
