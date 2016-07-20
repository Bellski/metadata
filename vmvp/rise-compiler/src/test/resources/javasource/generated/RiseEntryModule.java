package javasource;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.proxy.Proxy;

import javax.inject.Singleton;


@Module(
	includes = {
			RiseSubEntryModule.class,
			RiseSubSubEntryModule.class
	}
)
public class RiseEntryModule extends EntryModule {

	@Provides
	@Singleton
	EntryPresenter.Slot1 providesEntryPresenterSlot1(EntryPresenterSlot1 slot) {
		return slot;
	}

	@Provides
	@Singleton
	Proxy<EntryPresenter> providesRiseEntryPresenterProxy(RiseEntryPresenterProxy proxy) {
		return proxy;
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
