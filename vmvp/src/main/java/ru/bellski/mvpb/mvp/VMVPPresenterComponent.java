package ru.bellski.mvpb.mvp;

import com.vaadin.ui.Component;
import ru.bellski.mvpb.vaadin.IsComponent;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 09.07.2016.
 */
public class VMVPPresenterComponent<VIEW extends VMVPView> implements VMVPPresenter<VIEW>, IsComponent {
    private final VIEW view;

    @Inject
    public VMVPPresenterComponent(VIEW view) {
        this.view = view;
    }

    public VIEW getView() {
        return view;
    }

    @Override
    public Component asComponent() {
        return null;
    }
}
