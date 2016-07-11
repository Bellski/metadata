package ru.bellski.vmvp.proxy.events;

import ru.bellski.vmvp.mvp.VMVPPresenterImpl;
import ru.bellski.vmvp.mvp.slots.NestedSlot;

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