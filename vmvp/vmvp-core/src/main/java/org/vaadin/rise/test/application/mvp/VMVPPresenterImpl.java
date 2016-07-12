package org.vaadin.rise.test.application.mvp;


import org.vaadin.rise.test.application.mvp.slots.NestedSlot;
import org.vaadin.rise.test.application.proxy.Proxy;
import org.vaadin.rise.test.application.proxy.VMVPEventBus;
import org.vaadin.rise.test.application.proxy.events.RevealRootContentEvent;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 10.07.2016.
 */
public class VMVPPresenterImpl<VIEW extends VMVPView, PROXY extends Proxy<?>> extends VMVPPresenterComponent<VIEW> implements VMVPPresenter<VIEW> {
    public enum RevealType {
        Root
    }

    private final PROXY proxy;
    private RevealType revealType;
    private NestedSlot slot;


    public VMVPPresenterImpl(VMVPEventBus eventBus, VIEW view, PROXY proxy, NestedSlot slot) {
        this(eventBus, view, proxy, null, slot);
    }


    public VMVPPresenterImpl(VMVPEventBus eventBus, VIEW view, PROXY proxy, RevealType revealType) {
        this(eventBus, view, proxy, revealType, null);
    }

    @Inject
    public VMVPPresenterImpl(VMVPEventBus eventBus, VIEW view, PROXY proxy, RevealType revealType, NestedSlot slot) {
        super(view, eventBus);

        this.revealType = revealType;

        this.slot = slot;
        this.proxy = proxy;
    }

    public final void forceReveal() {
        if (isVisible()) {
            return;
        }
        revealInParent();
    }

    protected void revealInParent() {
        if (revealType != null) {
            switch (revealType) {
                case Root:
                    eventBus.post(new RevealRootContentEvent(this, this));
                    break;
            }
        } else {

        }
    }
}
