package org.vaadin.rise.place.deprecated.event;


import com.google.web.bindery.event.shared.Event;
import org.vaadin.rise.core.event.HasHandlers;
import org.vaadin.rise.place.deprecated.PlaceRequest;

public class PlaceRequestInternalEvent extends Event<PlaceRequestInternalHandler> {
    private static Type<PlaceRequestInternalHandler> TYPE;

    private final PlaceRequest request;
    private final boolean updateBrowserHistory;


    private boolean handled;
    private boolean authorized = true;

    public PlaceRequestInternalEvent(
            PlaceRequest request,
            boolean updateBrowserHistory) {
        this.request = request;
        this.updateBrowserHistory = updateBrowserHistory;
    }


    public static void fire(HasHandlers source, PlaceRequest request, boolean updateBrowserHistory) {
        source.fireEvent(new PlaceRequestInternalEvent(request, updateBrowserHistory));
    }

    public static Type<PlaceRequestInternalHandler> getType() {
        if (TYPE == null) {
            TYPE = new Type<>();
        }
        return TYPE;
    }

    @Override
    public Type<PlaceRequestInternalHandler> getAssociatedType() {
        return getType();
    }

    public PlaceRequest getRequest() {
        return request;
    }

    /**
     * Checks if the user was authorized to see the page.
     *
     * @return {@code true} if the user was authorized. {@code false} otherwise.
     */
    public boolean isAuthorized() {
        return authorized;
    }

    /**
     * Checks if the event was handled. If it was, then it should not be processed
     * further.
     *
     * @return {@code true} if the event was handled. {@code false} otherwise.
     */
    public boolean isHandled() {
        return handled;
    }

    public boolean shouldUpdateBrowserHistory() {
        return updateBrowserHistory;
    }

    /**
     * Indicates that the event was handled and that other handlers should not
     * process it.
     */
    public void setHandled() {
        handled = true;
    }

    /**
     * Indicates that the event was handled but that the user was not authorized
     * to getView the request page.
     */
    public void setUnauthorized() {
        authorized = false;
    }

    @Override
    protected void dispatch(PlaceRequestInternalHandler handler) {
        handler.onPlaceRequest(this);
    }
}