package ru.bellski.vmvp.proxy;

import com.google.gwt.thirdparty.guava.common.eventbus.Subscribe;
import ru.bellski.vmvp.proxy.events.PlaceRequestInternalEvent;


/**
 * Created by Aleksandr on 10.07.2016.
 */
public interface PlaceRequestInternalEvenListener {
    @Subscribe
    void onPlaceRequestInternalEven(PlaceRequestInternalEvent event);
}
