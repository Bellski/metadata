package generated;


import com.google.gwt.thirdparty.guava.common.collect.ImmutableSet;
import dagger.Module;
import dagger.Provides;
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
        return new PlaceTokenRegistry() {
            @Override
            public Set<String> getAllPlaceTokens() {
                return ImmutableSet.<String>builder()
                        .add("!claimlist")
                        .build();
            }
        };
    }
}
