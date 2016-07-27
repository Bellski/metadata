package javasource;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import org.vaadin.rise.deprecated.proxy.LazyPlacePresenter;
import org.vaadin.rise.place.PresenterPlace;
import org.vaadin.rise.place.annotation.Places;
import org.vaadin.rise.place.api.Place;

import javax.inject.Singleton;

import java.lang.String;

@Module(
	includes = {
			RiseSubEntryModule.class,
			RiseSubSubEntryModule.class
	}
)
public class RiseEntryModule extends EntryModule {

	public RiseEntryModule(String something) {
		super(something);
	}

	@Provides
	@Singleton
	EntryPresenter.Slot1 providesEntryPresenterSlot1(EntryPresenterSlot1 slot) {
		return slot;
	}

	@Provides @Singleton
	Entry.View providesEntryView(EntryView view) {
		return view;
	}

	@Provides @Singleton
	Entry.Presenter providesEntryPresenter(EntryPresenter presenter) {
		return presenter;
	}
}
