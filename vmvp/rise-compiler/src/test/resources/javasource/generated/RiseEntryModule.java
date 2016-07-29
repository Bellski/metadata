package javasource;

import dagger.Module;
import dagger.Provides;


import javax.inject.Singleton;

import javasource.RiseSubEntryModule;
import javasource.Entry.View;
import javasource.EntryModule;
import javasource.EntryPresenter;
import javasource.EntryPresenter.Slot1;
import javasource.RiseSubSubEntryModule;
import javasource.Entry.Presenter;
import javasource.EntryView;
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
