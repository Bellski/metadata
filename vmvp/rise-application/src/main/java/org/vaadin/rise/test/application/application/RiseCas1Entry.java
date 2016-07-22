
package org.vaadin.rise.test.application.application;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.proxy.Proxy;
import org.vaadin.rise.test.application.application.Cas1.Presenter;
import org.vaadin.rise.test.application.application.Cas1.View;
import org.vaadin.rise.test.application.application.Cas1Presenter.Slot1;
import org.vaadin.rise.test.application.application.claiminfo.RiseClaimInfoModule;
import org.vaadin.rise.test.application.application.claimlist.RiseClaimListModule;
import org.vaadin.rise.test.application.application.error.RiseErrorModule;

import javax.inject.Singleton;

@Module(includes = {RiseClaimInfoModule.class,RiseClaimListModule.class,RiseErrorModule.class})
public class RiseCas1Entry extends Cas1Entry {


    @Provides @Singleton
    Slot1 providesCas1PresenterSlot1(Cas1PresenterSlot1 slot) {
        return slot;
    }

    @Provides
    @Singleton
    Proxy<Cas1Presenter> providesRiseCas1PresenterProxy(RiseCas1PresenterProxy proxy) {
        return proxy;
    }

    @Provides
    @Singleton
    View providesCas1View(Cas1View view) {
        return view;
    }

    @Provides
    @Singleton
    Presenter providesCas1Presenter(Cas1Presenter presenter) {
        return presenter;
    }
}