package org.vaadin.rise.proxy.annotation;

import javax.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Aleksandr on 12.07.2016.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface Slot {

}
