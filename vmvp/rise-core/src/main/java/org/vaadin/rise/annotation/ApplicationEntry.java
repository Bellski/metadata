package org.vaadin.rise.annotation;

import org.vaadin.rise.core.annotation.NoPlaceBus;
import org.vaadin.rise.place.api.PlaceBus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by oem on 7/12/16.
 */
@Target(ElementType.TYPE )
@Retention(RetentionPolicy.CLASS)
public @interface ApplicationEntry {
	String contextRoot() default "!/";
	Class<? extends PlaceBus> usePlaceBus() default NoPlaceBus.class;
}
