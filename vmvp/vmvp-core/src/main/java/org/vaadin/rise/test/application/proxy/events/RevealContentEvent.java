package org.vaadin.rise.test.application.proxy.events;

import org.vaadin.rise.test.application.mvp.slots.NestedSlot;
import org.vaadin.rise.test.application.mvp.VMVPPresenterImpl;

public final class RevealContentEvent extends VMVPEvent {
    private final VMVPPresenterImpl<?, ?> content;
    private final NestedSlot type;

    public RevealContentEvent(Object source, VMVPPresenterImpl<?, ?> content, NestedSlot type) {
        super(source);
        this.content = content;
        this.type = type;
    }

    public VMVPPresenterImpl<?, ?> getContent() {
        return content;
    }
}