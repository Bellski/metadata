package org.vaadin.rise.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by oem on 7/29/16.
 */
@Target(ElementType.TYPE )
@Retention(RetentionPolicy.CLASS)
public @interface DefaultGateKeeper {

}
