package ru.bellski.metasql.generate;

import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.MethodSource;

/**
 * Created by Aleksandr on 30.05.2016.
 */
public class ParamSetterMethod {
    private final MethodSource<JavaInterfaceSource> setterSource;
    private final ParameterInterface parent;
    private String returnType;
    private String parameter;
    private String name;

    public ParamSetterMethod(ParameterInterface parent, MethodSource<JavaInterfaceSource> setterSource) {
        this.parent = parent;
        this.setterSource = setterSource;
        setterSource.setPublic();
    }


    public ParamSetterMethod setReturnType(String returnType) {
        this.returnType = returnType;
        setterSource.setReturnType(returnType);
        return this;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setParameter(String parameter) {
        setterSource.addParameter(parameter, "value");
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }

    public ParamSetterMethod setName(String name) {
        this.name = name;
        setterSource.setName(name);

        return this;
    }

    public String getName() {
        return name;
    }
}
