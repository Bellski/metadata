package org.vaadin.rise.proxy.slot.event;

import com.google.web.bindery.event.shared.Event;
import org.vaadin.rise.core.RisePresenterComponent;
import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.event.HasHandlers;


public final class RevealRootContentEvent extends Event<RevealRootContentHandler> {
    private static final Type<RevealRootContentHandler> TYPE = new Type<>();

    private final RisePresenterComponent<?> content;

    public RevealRootContentEvent(RisePresenterComponent<?> content) {
        this.content = content;
    }

    public static void fire(final HasHandlers source, final RisePresenterImpl<?> content) {
        source.fireEvent(new RevealRootContentEvent(content));
    }


    public RisePresenterComponent<?> getContent() {
        return content;
    }

    public static Type<RevealRootContentHandler> getType() {
        return TYPE;
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
