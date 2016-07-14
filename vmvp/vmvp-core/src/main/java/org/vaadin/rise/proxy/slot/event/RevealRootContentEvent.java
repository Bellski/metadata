package org.vaadin.rise.proxy.slot.event;

import com.google.web.bindery.event.shared.Event;
import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.event.HasHandlers;
import org.vaadin.rise.core.event.RiseEvent;
import org.vaadin.rise.test.application.mvp.VMVPPresenterImpl;
import org.vaadin.rise.test.application.proxy.events.VMVPEvent;

public final class RevealRootContentEvent extends Event<RevealRootContentHandler> {
    private static final Type<RevealRootContentHandler> TYPE = new Type<>();

    private final RisePresenterImpl<?> content;

    public RevealRootContentEvent(RisePresenterImpl<?> content) {
        this.content = content;
    }

    public static void fire(final HasHandlers source, final RisePresenterImpl<?> content) {
        source.fireEvent(new RevealRootContentEvent(content));
    }


    public RisePresenterImpl<?> getContent() {
        return content;
    }

    @Override
    public Type<RevealRootContentHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(RevealRootContentHandler handler) {
        handler.onRevealRootContent(this);
    }
}
