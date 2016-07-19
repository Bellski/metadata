package org.vaadin.rise.codegen.model;

/**
 * Created by Aleksandr on 16.07.2016.
 */
public class DaggerProvidesMethodModel {
    private final String methodName;
    private FqnHolder providesImpl;
    private FqnHolder providesInterface;

    public DaggerProvidesMethodModel(String methodName, FqnHolder providesImpl, FqnHolder providesInterface) {
        this.methodName = "provides" + methodName;
        this.providesImpl = providesImpl;
        this.providesInterface = providesInterface;
    }

    public String getMethodName() {
        return methodName;
    }

    public FqnHolder getProvidesImpl() {
        return providesImpl;
    }

    public FqnHolder getProvidesInterface() {
        return providesInterface;
    }
}
