package ru.bellski.metasql.lang.generator;

/**
 * Created by oem on 6/14/16.
 */
public class ParameterSetter {
    private String name;
    private String type;
    private String nextStep;

    public ParameterSetter(String name, String type, String nextStep) {
        this.name = name;
        this.type = type;
        this.nextStep = nextStep;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getNextStep() {
        return nextStep;
    }
}
