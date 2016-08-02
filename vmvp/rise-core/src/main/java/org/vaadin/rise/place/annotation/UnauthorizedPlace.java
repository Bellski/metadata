package org.vaadin.rise.place.annotation;

import javax.inject.Qualifier;
import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by oem on 7/11/16.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface UnauthorizedPlace {

}
