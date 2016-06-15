package ru.bellski.metasql.lang.generator;

import java.util.List;

/**
 * Created by oem on 6/14/16.
 */
public class SetParameterStep implements Step {
    private final String name;
    private final List<StepMethod> stepMethods;


    public SetParameterStep(List<StepMethod> stepMethods) {
        this.name = stepMethods.get(0).getName();
        this.stepMethods = stepMethods;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<StepMethod> getStepMethods() {
        return stepMethods;
    }
}
