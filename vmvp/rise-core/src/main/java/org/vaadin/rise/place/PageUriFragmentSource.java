package org.vaadin.rise.place;

import com.vaadin.server.Page;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public class PageUriFragmentSource extends BaseUriFragmentSource implements Page.UriFragmentChangedListener {
    private final Page page;

    @Inject
    public PageUriFragmentSource(Page page) {
        this.page = page;

        setUriFragment(page.getUriFragment());
        page.addUriFragmentChangedListener(this);
    }

    @Override
    public void setUriFragment(String historyToken, boolean update) {
        page.setUriFragment(historyToken, update);
        setUriFragment(historyToken);
    }


    @Override
    public void uriFragmentChanged(Page.UriFragmentChangedEvent event) {
        fireUriFragmentChange(event.getUriFragment());
    }
}
