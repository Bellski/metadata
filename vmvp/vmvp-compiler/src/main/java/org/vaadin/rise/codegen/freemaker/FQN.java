package org.vaadin.rise.codegen.freemaker;

/**
 * Created by Aleksandr on 16.07.2016.
 */
public class FQN {
    private String className;
    private String packageName;

    public FQN(String className, String packageName) {
        this.className = className;
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getFQN() {
        return packageName + "." + className;
    }

    public static FQN create(String className, String packageName) {
        return new FQN(className, packageName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FQN fqn = (FQN) o;

        if (!className.equals(fqn.className)) return false;
        return packageName.equals(fqn.packageName);

    }

    @Override
    public int hashCode() {
        int result = className.hashCode();
        result = 31 * result + packageName.hashCode();
        return result;
    }
}
