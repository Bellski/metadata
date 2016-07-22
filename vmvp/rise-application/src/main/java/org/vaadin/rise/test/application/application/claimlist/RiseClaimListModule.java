
package org.vaadin.rise.test.application.application.claimlist;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.proxy.Proxy;
import org.vaadin.rise.test.application.application.claimlist.ClaimList.Presenter;
import org.vaadin.rise.test.application.application.claimlist.ClaimList.View;

import javax.inject.Singleton;

@Module
public class RiseClaimListModule extends ClaimListModule {



    @Provides
    @Singleton
    Proxy<ClaimListPresenter> providesRiseClaimListPresenterProxy(RiseClaimListPresenterProxy proxy) {
        return proxy;
    }

    @Provides
    @Singleton
    View providesClaimListView(ClaimListView view) {
        return view;
    }

    @Provides
    @Singleton
    Presenter providesClaimListPresenter(ClaimListPresenter presenter) {
        return presenter;
    }
}