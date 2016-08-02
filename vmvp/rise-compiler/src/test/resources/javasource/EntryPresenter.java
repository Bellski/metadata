package javasource;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.RootPresenter;
import org.vaadin.rise.core.annotation.Presenter;
import org.vaadin.rise.slot.annotation.ThisIsNestedSlot;
import org.vaadin.rise.slot.api.IsNested;

import javax.inject.Inject;

/**
 * Created by oem on 7/15/16.
 */
@Presenter
public class EntryPresenter extends RisePresenterImpl<Entry.View> implements Entry.Presenter {
    @ThisIsNestedSlot
    public interface Slot1 extends IsNested<EntryPresenter> {}

    @Inject
    protected EntryPresenter(Entry.View view, RootPresenter.RootSlot rootSlot) {
        super(view, rootSlot);
    }
}
