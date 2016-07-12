package org.vaadin.rise.test.application.proxy.events;


import org.vaadin.rise.test.application.navigation.PlaceRequest;

/**
 * Created by Aleksandr on 10.07.2016.
 */
public class PlaceRequestInternalEvent extends VMVPEvent {
    private final PlaceRequest request;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public PlaceRequestInternalEvent(PlaceRequest request, Object source) {
        super(source);
        this.request = request;
    }

    public PlaceRequest getRequest() {
        return request;
    }
}
