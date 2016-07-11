package ru.bellski.mvpb.proxy;

import com.vaadin.server.Page;
import ru.bellski.mvpb.navigation.PlaceRequest;
import ru.bellski.mvpb.navigation.token.TokenFormatter;
import ru.bellski.mvpb.proxy.events.PlaceRequestInternalEvent;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandr on 10.07.2016.
 */
public class PlaceManagerImpl implements PlaceManager, Page.UriFragmentChangedListener{
    private final VMVPEventBus vmvpEventBus;
    private final TokenFormatter tokenFormatter;
    private List<PlaceRequest> placeHierarchy = new ArrayList<>();


    @Inject
    public PlaceManagerImpl(VMVPEventBus vmvpEventBus, TokenFormatter tokenFormatter) {
        this.vmvpEventBus = vmvpEventBus;
        this.tokenFormatter = tokenFormatter;
    }

    @Override
    public void uriFragmentChanged(Page.UriFragmentChangedEvent event) {
        handleTokenChange(event.getUriFragment());
    }

    @Override
    public PlaceRequest getCurrentPlaceRequest() {
        if (placeHierarchy.size() > 0) {
            return placeHierarchy.get(placeHierarchy.size() - 1);
        } else {
            return new PlaceRequest.Builder().build();
        }
    }

    @Override
    public List<PlaceRequest> getCurrentPlaceHierarchy() {
        return placeHierarchy;
    }

    private void handleTokenChange(final String historyToken) {
        if (historyToken.trim().isEmpty()) {

        } else {
            placeHierarchy = tokenFormatter.toPlaceRequestHierarchy(historyToken);
            doRevealPlace(getCurrentPlaceRequest(), true);
        }
    }

    protected void doRevealPlace(PlaceRequest request, boolean updateBrowserUrl) {
        PlaceRequestInternalEvent requestEvent = new PlaceRequestInternalEvent(request,
                updateBrowserUrl);
        vmvpEventBus.post(requestEvent);
    }
}
