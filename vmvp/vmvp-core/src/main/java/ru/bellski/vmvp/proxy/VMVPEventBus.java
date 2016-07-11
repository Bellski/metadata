package ru.bellski.vmvp.proxy;

import com.google.gwt.thirdparty.guava.common.eventbus.EventBus;
import ru.bellski.vmvp.proxy.events.VMVPEvent;

/**
 * Created by Aleksandr on 10.07.2016.
 */
public class VMVPEventBus {
    private final EventBus eventBus = new EventBus();


    public VMVPEventBus() {

    }

    public void register(Object object) {
        eventBus.register(object);
    }

    public void post(VMVPEvent event) {
        eventBus.post(event);
    }

    public void unregister(Object object) {
        eventBus.unregister(object);
    }
}
