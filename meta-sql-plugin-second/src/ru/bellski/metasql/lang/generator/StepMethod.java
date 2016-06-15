package ru.bellski.metasql.lang.generator;

/**
 * Created by oem on 6/15/16.
 */
public class StepMethod {
    private String name;
    private String returnType;
    private StepMethodParameter stepMethodParameter;

    public StepMethod(String name, String returnType, StepMethodParameter stepMethodParameter) {
        this.name = name;
        this.returnType = returnType;
        this.stepMethodParameter = stepMethodParameter;
    }

    public String getName() {
        return name;
    }

    public String getReturnType() {
        return returnType;
    }

    public StepMethodParameter getStepMethodParameter() {
        return stepMethodParameter;
    }
}
