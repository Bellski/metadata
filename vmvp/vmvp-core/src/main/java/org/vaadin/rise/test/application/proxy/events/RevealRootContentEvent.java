package org.vaadin.rise.test.application.proxy.events;

import org.vaadin.rise.test.application.mvp.VMVPPresenterImpl;

public final class RevealRootContentEvent extends VMVPEvent{

    private final VMVPPresenterImpl<?, ?> content;

    public RevealRootContentEvent(Object source, VMVPPresenterImpl<?, ?> content) {
        super(source);
        this.content = content;
    }

    public VMVPPresenterImpl<?, ?> getContent() {
        return content;
    }

}
