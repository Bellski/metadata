package ru.bellski.metasql.generate;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

/**
 * Created by Aleksandr on 30.05.2016.
 */
public class ParameterInterface {
    private final JavaInterfaceSource interfaceSource;
    private final String packageName;
    private final String name;
    private ParamSetterMethod paramSetter;

    private ParameterInterface(String name, String packageName, JavaInterfaceSource interfaceSource) {
        this.interfaceSource = interfaceSource;
        this.name = name;
        this.packageName = packageName;

        interfaceSource.setName(name);
        interfaceSource.setPackage(packageName);
    }

    public static ParameterInterface create(String name, String packageName) {
        return new ParameterInterface(name, packageName, Roaster.create(JavaInterfaceSource.class));
    }

    public ParameterInterface addImport(String importName) {
        interfaceSource.addImport(packageName + "." + importName);
        return this;
    }

    public ParamSetterMethod createSetter() {
        final ParamSetterMethod paramSetter = new ParamSetterMethod(this, interfaceSource.addMethod());
        this.paramSetter = paramSetter;
        return paramSetter;
    }

    public JavaInterfaceSource getType() {
        return interfaceSource;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getName() {
        return name;
    }

    public String getJavaFileName() {
        return name + ".java";
    }

    public ParamSetterMethod getParamSetter() {
        return paramSetter;
    }

    @Override
    public String toString() {
        return interfaceSource.toString();
    }
}
