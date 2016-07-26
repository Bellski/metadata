package javasource;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

import java.lang.String;

import org.vaadin.rise.deprecated.proxy.Proxy;


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
