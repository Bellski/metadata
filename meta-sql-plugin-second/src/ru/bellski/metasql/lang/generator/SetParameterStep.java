package ru.bellski.metasql.lang.generator;

import java.util.List;

/**
 * Created by oem on 6/14/16.
 */
public class SetParameterStep {
    private final String name;
    private List<ParameterSetter> parameterSetterList;

    public SetParameterStep(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<ParameterSetter> getParameterSetterList() {
        return parameterSetterList;
    }

    public void setParameterSetterList(List<ParameterSetter> parameterSetterList) {
        this.parameterSetterList = parameterSetterList;
    }
}
