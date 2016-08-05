package org.vaadin.rise.plugin.idea.model;

/**
 * Created by oem on 8/4/16.
 */
public class PsiClassModel {
    private String name;
    private final String packageName;
    private String fqn;

    public PsiClassModel(String name, String packageName) {
        this.name = name;
        this.packageName = packageName;
        this.fqn = buildFqn(name);
    }

    public PsiClassModel(String packageName) {
        this.packageName = packageName;
    }

    public void setName(String name) {
        this.name = name;
        this.fqn = buildFqn(name);
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getFqn() {
        return fqn;
    }

    private String buildFqn(String name) {
        return packageName.concat(".").concat(name);
    }
}
