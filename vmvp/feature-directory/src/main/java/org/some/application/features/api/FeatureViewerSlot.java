package org.some.application.features.api;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.slot.api.IsNested;

/**
 * Created by oem on 7/27/16.
 */
public interface FeatureViewerSlot<Presenter_ extends RisePresenterImpl<?>> extends IsNested<Presenter_> {
}
