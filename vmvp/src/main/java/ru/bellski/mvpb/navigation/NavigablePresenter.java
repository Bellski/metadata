package ru.bellski.mvpb.navigation;

import ru.bellski.mvpb.mvp.VMVPPresenterImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Aleksandr on 10.07.2016.
 */
@Singleton
public class NavigablePresenter<PRESENTER extends VMVPPresenterImpl<?,?>> implements Place {
    private final String token;

    @Inject
    public NavigablePresenter(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
