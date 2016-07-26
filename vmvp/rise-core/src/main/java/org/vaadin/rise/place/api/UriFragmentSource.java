package org.vaadin.rise.place.api;

/**
 * Created by Aleksandr on 25.07.2016.
 */
public interface UriFragmentSource {
    String getUriFragment();
    void setUriFragmentChangeListener(UriFragmentChangeListener listener);
    void setUriFragment(String historyToken, boolean update);
}
