package org.vaadin.rise.core;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.SingleComponentContainer;
import com.vaadin.ui.UI;
import org.vaadin.rise.deprecated.proxy.slot.IsSingleSlot;
import org.vaadin.rise.deprecated.proxy.slot.OrderedSlot;
import org.vaadin.rise.vaadin.IsComponent;;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oem on 7/12/16.
 */
public class RiseViewImpl<PRESENTER extends RisePresenter<?>> implements RiseView<PRESENTER> {
    private final Map<Object, SingleComponentContainer> oneComponentSlots = new HashMap<>();
    private final Map<Object, ComponentContainer> componentContainerSlots = new HashMap<>();
    private final Map<OrderedSlot<?>, List<Comparable<Comparable<?>>>> orderedSlots
            = new HashMap<>();

    private final UI ui;

    private Component component;
    private PRESENTER presenter;


    public RiseViewImpl(UI ui) {
        this.ui = ui;
    }

    protected void initComponent(Component component) {
        this.component = component;
    }

    @Override
    public void addToSlot(Object slot, IsComponent content) {
        if (componentContainerSlots.containsKey(slot)) {
            if (orderedSlots.containsKey(slot)) {
                List<Comparable<Comparable<?>>> list = orderedSlots.get(slot);
                list.add((Comparable<Comparable<?>>) content);
                Collections.sort(list);
                int index = Collections.binarySearch(list, (Comparable<Comparable<?>>) content);

                ComponentContainer insertPanel = componentContainerSlots.get(slot);
                //TODO: положить пирожок
            } else {
                componentContainerSlots.get(slot).addComponent(content.asComponent());
            }
        }
    }

    @Override
    public void removeFromSlot(Object slot, IsComponent content) {
        if (oneComponentSlots.containsKey(slot)) {
            if (oneComponentSlots.get(slot).getContent() == content.asComponent()) {
                oneComponentSlots.get(slot).setContent(null);
            }
        } else if (componentContainerSlots.containsKey(slot)) {
            componentContainerSlots.get(slot).removeComponent(content.asComponent());
            if (orderedSlots.containsKey(slot)) {
                orderedSlots.get(slot).remove(content);
            }
        }
    }

    @Override
    public void setInSlot(Object slot, IsComponent content) {
        if (oneComponentSlots.containsKey(slot)) {
            oneComponentSlots.get(slot).setContent(content.asComponent());
        } else if (componentContainerSlots.containsKey(slot)) {
            componentContainerSlots.get(slot).removeAllComponents();
            if (content != null) {
                componentContainerSlots.get(slot).addComponent(content.asComponent());
            }
            if (orderedSlots.containsKey(slot)) {
                orderedSlots.get(slot).clear();
                if (content != null) {
                    orderedSlots.get(slot).add((Comparable<Comparable<?>>) content);
                }
            }
        }
    }

    protected void bindSlot(IsSingleSlot<?> slot, Object container) {
        internalBindSlot(slot, container);
    }


    @Override
    public Component asComponent() {
        return component;
    }

    private void internalBindSlot(Object slot, Object container) {
        if (container instanceof SingleComponentContainer) {
            oneComponentSlots.put(slot, (SingleComponentContainer) container);
        } else if (container instanceof ComponentContainer) {
            componentContainerSlots.put(slot, (ComponentContainer) container);
        } else {
            throw new IllegalArgumentException("Containers must implement either HasOneWidget or HasWidgets.");
        }
    }


    public PRESENTER getPresenter() {
        return presenter;
    }


    @Override
    public void setPresenter(PRESENTER presenter) {
        this.presenter = presenter;
    }

    protected UI getUI() {
        return ui;
    }
}
