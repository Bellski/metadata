package ru.bellski.metasql.generate;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Aleksandr on 30.05.2016.
 */
public class ExecutionStepInterface {
    private final JavaInterfaceSource interfaceSource;
    private final String name;
    private final String packageName;

    private ExecutionStepInterface(String name, String packageName, JavaInterfaceSource interfaceSource) {
        this.interfaceSource = interfaceSource;
        this.name = name;
        this.packageName = packageName;

        interfaceSource.setPackage(packageName);
        interfaceSource.setName(name + "Execution");

        initExecutionMethods();
    }

    private void initExecutionMethods() {
        interfaceSource.addMethod().setPublic().setReturnType("void").setName("execute").addThrows(SQLException.class);

        interfaceSource.addMethod().setPublic().setReturnType("void").setName("execute").addThrows(SQLException.class).addParameter(Connection.class, "connection");
    }

    public static ExecutionStepInterface create(String name, String packageName) {
        return new ExecutionStepInterface(name, packageName, Roaster.create(JavaInterfaceSource.class));
    }

    public JavaInterfaceSource getType() {
        return interfaceSource;
    }

    public String getName() {
        return name;
    }

    public String getFileName() {
        return name + ".java";
    }

    public String getPackageName() {
        return packageName;
    }

    @Override
    public String toString() {
        return interfaceSource.toString();
    }
}
