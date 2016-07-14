package generated.org.vaadin.rise.test.application.claiminfo;

import dagger.Lazy;
import org.vaadin.rise.place.DefaultPlaceManager;
import org.vaadin.rise.place.PlaceImpl;
import org.vaadin.rise.proxy.ProxyPlaceImpl;
import org.vaadin.rise.test.application.application.claiminfo.ClaimInfoPresenter;
import org.vaadin.rise.test.application.application.claimlist.ClaimListPresenter;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 13.07.2016.
 */
public class RiseCas1ClaimInfoPresenterPlaceProxy extends ProxyPlaceImpl<ClaimInfoPresenter> {

    @Inject
    public RiseCas1ClaimInfoPresenterPlaceProxy(DefaultPlaceManager placeManager, Lazy<ClaimInfoPresenter> cas1Presenter) {
        super(new PlaceImpl("!claiminfo"), placeManager, cas1Presenter);
    }
}
