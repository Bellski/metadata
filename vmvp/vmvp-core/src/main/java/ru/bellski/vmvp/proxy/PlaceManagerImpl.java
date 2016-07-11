package ru.bellski.vmvp.proxy;

import com.vaadin.server.Page;
import ru.bellski.vmvp.navigation.PlaceRequest;
import ru.bellski.vmvp.navigation.token.TokenFormatter;
import ru.bellski.vmvp.proxy.events.PlaceRequestInternalEvent;


import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandr on 10.07.2016.
 */
public abstract class PlaceManagerImpl implements PlaceManager, Page.UriFragmentChangedListener{
    private final VMVPEventBus vmvpEventBus;
    private final TokenFormatter tokenFormatter;
    private final Page page;
    private List<PlaceRequest> placeHierarchy = new ArrayList<>();


    public PlaceManagerImpl(Page page, VMVPEventBus vmvpEventBus, TokenFormatter tokenFormatter) {
        this.page = page;
        this.vmvpEventBus = vmvpEventBus;
        this.tokenFormatter = tokenFormatter;
        page.addUriFragmentChangedListener(this);
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

    @Override
    public void revealCurrentPlace() {
        handleTokenChange(page.getUriFragment());
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

    @Override
    public void revealPlace(PlaceRequest request, boolean updateBrowserUrl) {
        placeHierarchy.clear();
        placeHierarchy.add(request);
        doRevealPlace(request, updateBrowserUrl);
    }

    @Override
    public void revealPlace(PlaceRequest request) {
        revealPlace(request, true);
    }
}
