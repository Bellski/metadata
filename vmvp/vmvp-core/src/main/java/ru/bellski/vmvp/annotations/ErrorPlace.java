package ru.bellski.vmvp.annotations;

import javax.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by oem on 7/11/16.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface ErrorPlace {

}
