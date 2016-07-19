package javasource;

import com.vaadin.ui.UI;
import org.vaadin.rise.core.RisePresenter;
import org.vaadin.rise.core.RiseViewImpl;

/**
 * Created by oem on 7/15/16.
 */
public class EntryView extends RiseViewImpl<Entry.Presenter> implements Entry.View {

	public EntryView(UI ui) {
		super(ui);
	}
}
