package org.vaadin.rise.test.application.proxy.events;

import java.util.EventObject;

/**
 * Created by Aleksandr on 10.07.2016.
 */
public class VMVPEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public VMVPEvent(Object source) {
        super(source);
    }
}
