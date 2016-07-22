
package org.vaadin.rise.test.application.application;


import org.vaadin.rise.test.application.application.claiminfo.RiseClaimInfoPresenterProxy;
import org.vaadin.rise.test.application.application.claiminfo.clam.RiseClaimPresenterProxy;
import org.vaadin.rise.test.application.application.claimlist.RiseClaimListPresenterProxy;
import org.vaadin.rise.test.application.application.error.RiseErrorPresenterProxy;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class EagerProxies {

    @Inject
    RiseErrorPresenterProxy RiseErrorPresenterProxy;
    @Inject
    RiseCas1PresenterProxy RiseCas1PresenterProxy;
    @Inject
    RiseClaimPresenterProxy RiseClaimPresenterProxy;
    @Inject
    RiseClaimListPresenterProxy RiseClaimListPresenterProxy;
    @Inject
    RiseClaimInfoPresenterProxy RiseClaimInfoPresenterProxy;

    @Inject
    public EagerProxies() {
    }
}
