package ru.bellski.metasql.lang.generator.builder;

/**
 * Created by oem on 6/16/16.
 */
public class StepBuilder {
    private final String name;
    private final int index;
    private String setterName;
    private String setterParamType;
    private String setterParamName;
    private String setterReturnType;


    public StepBuilder(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }


    public void setSetterName(String setterName) {
        this.setterName = setterName;
    }

    public String getSetterName() {
        return setterName;
    }

    public void setSetterParamType(String setterParamType) {
        this.setterParamType = setterParamType;
    }

    public String getSetterParamType() {
        return setterParamType;
    }

    public void setSetterParamName(String setterParamName) {
        this.setterParamName = setterParamName;
    }

    public String getSetterParamName() {
        return setterParamName;
    }

    public void setSetterReturnType(String setterReturnType) {
        this.setterReturnType = setterReturnType;
    }

    public String getSetterReturnType() {
        return setterReturnType;
    }

    public String getOverrideSetter() {
        return
                "    @Override \n"
                + "    public " + setterReturnType + " " + setterName + "(" + setterParamType + " " + setterParamName + ") { \n"
                + "        params[" + index + "] = " + setterParamName + "; \n"
                + "        return this; \n"
                + "    }"
                ;
    }

    @Override
    public String toString() {
        return "public interface " + name + " { \n"
                + "    " + setterReturnType + " " + setterName + "(" + setterParamType + " " + setterParamName + ");\n"
                + "}"
                ;
    }
}
