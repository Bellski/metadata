
package org.vaadin.rise.test.application.application.claiminfo.clam;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.proxy.Proxy;
import org.vaadin.rise.test.application.application.claiminfo.clam.Claim.Presenter;
import org.vaadin.rise.test.application.application.claiminfo.clam.Claim.View;

import javax.inject.Singleton;

@Module
public class RiseClaimModule extends ClaimModule {



    @Provides
    @Singleton
    Proxy<ClaimPresenter> providesRiseClaimPresenterProxy(RiseClaimPresenterProxy proxy) {
        return proxy;
    }

    @Provides
    @Singleton
    View providesClaimView(ClaimView view) {
        return view;
    }

    @Provides
    @Singleton
    Presenter providesClaimPresenter(ClaimPresenter presenter) {
        return presenter;
    }
}