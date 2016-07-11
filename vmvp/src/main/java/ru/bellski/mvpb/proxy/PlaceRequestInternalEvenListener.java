package ru.bellski.mvpb.proxy;

import com.google.gwt.thirdparty.guava.common.eventbus.Subscribe;
import ru.bellski.mvpb.proxy.events.PlaceRequestInternalEvent;

/**
 * Created by Aleksandr on 10.07.2016.
 */
public interface PlaceRequestInternalEvenListener {
    @Subscribe
    void onPlaceRequestInternalEven(PlaceRequestInternalEvent event);
}
