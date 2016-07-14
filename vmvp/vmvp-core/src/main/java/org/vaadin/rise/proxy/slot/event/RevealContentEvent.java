package org.vaadin.rise.proxy.slot.event;

import com.google.web.bindery.event.shared.Event;
import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.event.HasHandlers;
import org.vaadin.rise.proxy.slot.NestedSlot;
import org.vaadin.rise.proxy.slot.handler.RevealContentHandler;

/**
 * Created by Aleksandr on 13.07.2016.
 */
public class RevealContentEvent extends Event<RevealContentHandler> {

    private final NestedSlot type;
    private final RisePresenterImpl content;

    public RevealContentEvent(NestedSlot type, RisePresenterImpl content) {
        this.type = type;
        this.content = content;
    }


    public static void fire(HasHandlers source, NestedSlot type, RisePresenterImpl content) {
        source.fireEvent(new RevealContentEvent(type, content));
    }

    @Override
    public NestedSlot getAssociatedType() {
        return type;
    }

    public RisePresenterImpl<?> getContent() {
        return content;
    }

    @Override
    protected void dispatch(RevealContentHandler handler) {
        handler.onRevealContent(this);
    }
}
