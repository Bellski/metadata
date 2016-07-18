package javasource;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

import javasource.NestedSlotEntryPresenterSlot1;
import javasource.RiseSubEntryModule;
import javasource.EntryModule;
import javasource.EntryPresenter;
import javasource.EntryPresenter.Slot1;
import javasource.RiseSubSubEntryModule;
import javasource.EntryView;


@Module(
	includes = {
			RiseSubEntryModule.class,
			RiseSubSubEntryModule.class
	}
)
public class RiseEntryModule extends EntryModule {

	@Provides
	@Singleton
	EntryPresenter.Slot1 providesNestedSlotEntryPresenterSlot1(NestedSlotEntryPresenterSlot1 slot) {
		return slot;
	}

	@Provides @Singleton
	EntryView providesEntryView(EntryView view) {
		return view;
	}

	@Provides @Singleton
	EntryPresenter providesEntryPresenter(EntryPresenter presenter) {
		return presenter;
	}
}
