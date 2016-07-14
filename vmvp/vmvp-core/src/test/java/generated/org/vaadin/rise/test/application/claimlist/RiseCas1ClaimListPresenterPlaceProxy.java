package generated.org.vaadin.rise.test.application.claimlist;

import dagger.Lazy;
import org.vaadin.rise.core.event.RiseEventBus;
import org.vaadin.rise.place.DefaultPlaceManager;
import org.vaadin.rise.place.PlaceImpl;
import org.vaadin.rise.proxy.ProxyPlaceImpl;
import org.vaadin.rise.test.application.claimlist.ClaimListPresenter;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 13.07.2016.
 */
public class RiseCas1ClaimListPresenterPlaceProxy extends ProxyPlaceImpl<ClaimListPresenter>  {

    @Inject
    public RiseCas1ClaimListPresenterPlaceProxy(DefaultPlaceManager placeManager, RiseEventBus eventBus, Lazy<ClaimListPresenter> cas1Presenter) {
        super(new PlaceImpl("!claimlist"), placeManager, cas1Presenter, eventBus);
    }
}
