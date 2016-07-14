package org.vaadin.rise.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Aleksandr on 12.07.2016.
 */
@Target(ElementType.TYPE )
@Retention(RetentionPolicy.CLASS )
public @interface RiseModule {
    Class<?> view();
    Class<?> presenter();
    Class<?> parent() default NoParent.class;
}
