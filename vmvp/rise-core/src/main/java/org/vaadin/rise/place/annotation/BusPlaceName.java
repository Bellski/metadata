package org.vaadin.rise.place.annotation;

import javax.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Aleksandr on 26.07.2016.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface BusPlaceName {
}
