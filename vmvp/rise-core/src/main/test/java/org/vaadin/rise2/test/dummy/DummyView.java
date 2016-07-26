package org.vaadin.rise2.test.dummy;

import com.vaadin.ui.UI;
import org.vaadin.rise.core.RiseViewImpl;
import org.vaadin.rise2.test.place.PlaceTest;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public class DummyView extends RiseViewImpl<DummyPresenter> {
    public DummyView(UI ui) {
        super(ui);
    }
}
