package org.vaadin.rise.core.event;

import com.google.web.bindery.event.shared.Event;

/**
 * Created by Aleksandr on 13.07.2016.
 */
public interface HasHandlers {
    void fireEvent(Event<?> event);
}
