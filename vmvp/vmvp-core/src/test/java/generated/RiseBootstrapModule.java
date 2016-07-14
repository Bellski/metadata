package generated;


import com.google.gwt.thirdparty.guava.common.collect.ImmutableSet;
import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.core.Root;
import org.vaadin.rise.core.RootPresenter;
import org.vaadin.rise.core.RootView;
import org.vaadin.rise.place.token.PlaceTokenRegistry;

import javax.inject.Singleton;
import java.util.Set;

/**
 * Created by Aleksandr on 13.07.2016.
 */
@Module
public class RiseBootstrapModule {

    @Provides @Singleton
    PlaceTokenRegistry providesPlaceTokenRegistry() {
        return () ->
            ImmutableSet.<String>builder()
                .add("!claimlist")
				.build();
    }

    @Provides @Singleton
    RootPresenter.RootSlot providesRootSlot(RootPresenter.RiseRootSlot rootSlot) {
        return rootSlot;
    }

    @Provides @Singleton
    Root.Presenter providesRootPresenter(RootPresenter rootPresenter) {
        return rootPresenter;
    }

    @Provides @Singleton
    Root.View providesRootView(RootView rootView) {
        return rootView;
    }
}
