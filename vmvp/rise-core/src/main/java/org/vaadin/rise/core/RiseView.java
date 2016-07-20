package org.vaadin.rise.core;


import org.vaadin.rise.vaadin.IsComponent;

/**
 * Created by oem on 7/12/16.
 */
public interface RiseView<PRESENTER extends RisePresenter<?>> extends IsComponent {
    void addToSlot(Object slot, IsComponent content);
    void removeFromSlot(Object slot, IsComponent content);
    void setInSlot(Object slot, IsComponent content);

    void setPresenter(PRESENTER presenter);
}
