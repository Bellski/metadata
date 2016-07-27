package org.vaadin.rise2.test.place;

import org.vaadin.rise.place.BaseUriFragmentSource;

/**
 * Created by Aleksandr on 25.07.2016.
 */
public class FakeUriFragmentSource extends BaseUriFragmentSource  {

    @Override
    public void fireUriFragmentChange(String uriFragment) {
        super.fireUriFragmentChange(uriFragment);
    }

    @Override
    public void setUriFragment(String historyToken, boolean update) {
        fireUriFragmentChange(historyToken);
    }
}
