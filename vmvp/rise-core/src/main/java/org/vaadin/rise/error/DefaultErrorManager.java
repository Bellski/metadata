package org.vaadin.rise.error;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by oem on 8/2/16.
 */
@Target(ElementType.TYPE )
@Retention(RetentionPolicy.CLASS)
public @interface DefaultErrorManager {
}
