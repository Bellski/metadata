package ru.bellski.metasql.lang.generator;

import java.util.List;

/**
 * Created by oem on 6/15/16.
 */
public interface Step {
    String getName();
    List<StepMethod> getStepMethods();
}
