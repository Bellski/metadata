package ru.bellski.vmvp.navigation.token;

import dagger.Module;

import javax.inject.Singleton;
import java.util.Set;

/**
 * Provide information about all registered place tokens.
 */
@Module
public class PlaceTokenRegistry {



    @Singleton
    public Set<String> getAllPlaceTokens() {
        return null;
    }

}
