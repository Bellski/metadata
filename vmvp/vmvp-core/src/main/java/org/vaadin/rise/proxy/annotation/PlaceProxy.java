package org.vaadin.rise.proxy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Aleksandr on 13.07.2016.
 */
@Target(ElementType.TYPE )
@Retention(RetentionPolicy.CLASS )
public @interface PlaceProxy {
    String placeName();
}
