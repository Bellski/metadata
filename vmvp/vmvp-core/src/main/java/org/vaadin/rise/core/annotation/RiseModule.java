package org.vaadin.rise.core.annotation;

import org.vaadin.rise.core.RisePresenter;
import org.vaadin.rise.core.RisePresenterComponent;
import org.vaadin.rise.core.RiseView;
import org.vaadin.rise.core.RiseViewImpl;

import javax.lang.model.type.TypeMirror;
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
    Class<? extends RiseViewImpl> view();
    Class<? extends RiseView> viewApi();
    Class<? extends RisePresenterComponent> presenter();
    Class<? extends RisePresenter> presenterApi();
    Class<?> parent() default NoParent.class;
}
