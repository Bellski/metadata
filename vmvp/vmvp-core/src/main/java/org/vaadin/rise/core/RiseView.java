package org.vaadin.rise.core;

import org.vaadin.rise.test.application.vaadin.IsComponent;

/**
 * Created by oem on 7/12/16.
 */
public interface RiseView extends IsComponent{
    void addToSlot(Object slot, IsComponent content);
    void removeFromSlot(Object slot, IsComponent content);
    void setInSlot(Object slot, IsComponent content);
}
