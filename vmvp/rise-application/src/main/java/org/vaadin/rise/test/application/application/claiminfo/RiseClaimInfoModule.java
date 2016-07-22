
package org.vaadin.rise.test.application.application.claiminfo;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.proxy.Proxy;
import org.vaadin.rise.test.application.application.claiminfo.ClaimInfo.Presenter;
import org.vaadin.rise.test.application.application.claiminfo.ClaimInfo.View;
import org.vaadin.rise.test.application.application.claiminfo.ClaimInfoPresenter.Slot2;
import org.vaadin.rise.test.application.application.claiminfo.clam.RiseClaimModule;

import javax.inject.Singleton;

@Module(includes = {RiseClaimModule.class})
public class RiseClaimInfoModule extends ClaimInfoModule {


    @Provides @Singleton
    Slot2 providesClaimInfoPresenterSlot2(ClaimInfoPresenterSlot2 slot) {
        return slot;
    }

    @Provides
    @Singleton
    Proxy<ClaimInfoPresenter> providesRiseClaimInfoPresenterProxy(RiseClaimInfoPresenterProxy proxy) {
        return proxy;
    }

    @Provides
    @Singleton
    View providesClaimInfoView(ClaimInfoView view) {
        return view;
    }

    @Provides
    @Singleton
    Presenter providesClaimInfoPresenter(ClaimInfoPresenter presenter) {
        return presenter;
    }
}