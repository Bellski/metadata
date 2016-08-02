package org.vaadin.rise.place;

import org.vaadin.rise.place.api.UriFragmentChangeListener;
import org.vaadin.rise.place.api.UriFragmentSource;

/**
 * Created by Aleksandr on 25.07.2016.
 */
public class BaseUriFragmentSource implements UriFragmentSource {
    private UriFragmentChangeListener uriFragmentChangeListener;
    private String currentUriFragment = "";


    @Override
    public String getUriFragment() {
        return currentUriFragment;
    }

    protected void setUriFragment(String uriFragment) {
//        if (uriFragment.equals("!/") || uriFragment.equals("!") ) {
//            uriFragment = "";
//        }

        if (!uriFragment.isEmpty() && uriFragment.charAt(uriFragment.length() -1) == '/') {
            uriFragment = uriFragment.substring(0, uriFragment.length() -1);
        }

        currentUriFragment = uriFragment;
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
        setUriFragment(uriFragment);

        if (uriFragmentChangeListener == null) {
            throw new NullPointerException(
                    "BaseUriFragmentSource.uriFragmentChangeListener can't be null!"
            );
        }

        uriFragmentChangeListener.onUriFragmentChanged(currentUriFragment);
    }
}
