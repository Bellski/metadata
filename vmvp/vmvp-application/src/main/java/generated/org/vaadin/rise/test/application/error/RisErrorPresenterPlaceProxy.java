package generated.org.vaadin.rise.test.application.error;

import dagger.Lazy;
import org.vaadin.rise.place.DefaultPlaceManager;
import org.vaadin.rise.place.PlaceImpl;
import org.vaadin.rise.proxy.ProxyPlaceImpl;
import org.vaadin.rise.test.application.application.claiminfo.ClaimInfoPresenter;
import org.vaadin.rise.test.application.application.error.ErrorPresenter;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 13.07.2016.
 */
public class RisErrorPresenterPlaceProxy extends ProxyPlaceImpl<ErrorPresenter> {

    @Inject
    public RisErrorPresenterPlaceProxy(DefaultPlaceManager placeManager, Lazy<ErrorPresenter> cas1Presenter) {
        super(new PlaceImpl("!error"), placeManager, cas1Presenter);
    }
}
