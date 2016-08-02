package org.vaadin.rise.place.annotation;

import javax.inject.Qualifier;
import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface DefaultPlace {

}