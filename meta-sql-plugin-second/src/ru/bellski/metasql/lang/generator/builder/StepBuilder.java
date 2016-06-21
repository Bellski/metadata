package ru.bellski.metasql.lang.generator.builder;

import com.intellij.openapi.util.text.StringUtil;
import ru.bellski.metasql.lang.psi.MetaSqlParameterDefinition;

/**
 * Created by oem on 6/16/16.
 */
public class StepBuilder {
    private final String name;
    private final int index;
    private final String setterName;
    private final String setterParamType;
    private final String setterParamName;
    private String setterReturnType;


    public StepBuilder(MetaSqlParameterDefinition parameterDefinition, int index) {
        this.name = StringUtil.capitalize(parameterDefinition.getName());
        this.setterName = "set".concat(name);
        this.setterParamType = parameterDefinition.getLiteralType().getTypeName();
        this.setterParamName = parameterDefinition.getName();
        this.index = index;
    }

    public String getName() {
        return name;
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
