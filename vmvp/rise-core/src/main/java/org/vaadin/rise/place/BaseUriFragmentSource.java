package org.vaadin.rise.place;

import org.vaadin.rise.place.api.UriFragmentChangeListener;
import org.vaadin.rise.place.api.UriFragmentSource;

/**
 * Created by Aleksandr on 25.07.2016.
 */
public class BaseUriFragmentSource implements UriFragmentSource {
    private UriFragmentChangeListener uriFragmentChangeListener;
    protected String currentUriFragment = "";


    @Override
    public String getUriFragment() {
        return currentUriFragment;
    }

    @Override
    public void setUriFragmentChangeListener(UriFragmentChangeListener listener) {
        this.uriFragmentChangeListener = listener;
    }

    @Override
    public void setUriFragment(String historyToken, boolean update) {
        throw new UnsupportedOperationException();
    }

    protected void fireUriFragmentChange(String uriFragment) {
        if (uriFragmentChangeListener == null) {
            throw new NullPointerException(
                    "BaseUriFragmentSource.uriFragmentChangeListener can't be null!"
            );
        }

        currentUriFragment = uriFragment;

        uriFragmentChangeListener.onUriFragmentChanged(uriFragment);
    }
}
