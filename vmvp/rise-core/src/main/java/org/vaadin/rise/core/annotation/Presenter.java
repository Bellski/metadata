package org.vaadin.rise.core.annotation;

import org.vaadin.rise.security.Gatekeeper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Aleksandr on 14.07.2016.
 */

@Target(ElementType.TYPE )
@Retention(RetentionPolicy.CLASS)
public @interface Presenter {
    String placeName() default "";
    Class<? extends Gatekeeper> useGateKeeper() default NoGateKeeper.class;
    boolean defaultPlace() default false;
    boolean errorPlace() default false;
    boolean authorizePlace() default false;
    boolean placeBus() default false;
}
