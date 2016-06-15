package ru.bellski.metasql.lang.generator;

/**
 * Created by oem on 6/15/16.
 */
public class StepMethodParameter {
    private String type;
    private String name;

    public StepMethodParameter() {
    }

    public StepMethodParameter(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public boolean isVoid() {
        return name == null || type == null;
    }
}
