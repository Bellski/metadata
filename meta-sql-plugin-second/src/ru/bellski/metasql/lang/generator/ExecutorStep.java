package ru.bellski.metasql.lang.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oem on 6/15/16.
 */
public class ExecutorStep implements Step {
    private final String name;
    private final List<StepMethod> stepMethods;

    public ExecutorStep(String className, String returnType) {
        this.name = className.concat("Executor");

        stepMethods = new ArrayList<>(2);

        stepMethods.add(new StepMethod("execute", returnType, new StepMethodParameter()));
        stepMethods.add(new StepMethod("execute", returnType, new StepMethodParameter("ds", "Connection")));
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
