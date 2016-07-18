package org.vaadin.rise.codegen.freemaker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Aleksandr on 16.07.2016.
 */
public class ModuleModel {
    private String className;
    private final String extendsModule;
    private String packageName;
    private Set<String> imports = new HashSet<>(2);
    private List<String> includes = new ArrayList<>(2);
    private List<ProvidesSlotModel> slots = new ArrayList<>(2);

    private ProvidesVPModel providesView;
    private ProvidesVPModel providesPresenter;


    public ModuleModel(FQN riseModule, FQN extendsModule) {
        this.className = riseModule.getClassName();
        this.extendsModule = extendsModule.getClassName();

        packageName = riseModule.getPackageName();

        imports.add(extendsModule.getFQN());
    }

    public String getClassName() {
        return className;
    }

    public String getExtendsModule() {
        return extendsModule;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Set<String> getImports() {
        return imports;
    }

    public void addImport(String importName) {
        imports.add(importName);
    }

    public boolean hasIncludes() {
        return !includes.isEmpty();
    }

    public List<String> getIncludes() {
        return includes;
    }

    public String getJoinedIncludes() {
        return includes
                .stream()
                .collect(Collectors.joining(","));
    }

    public void include(ModuleModel include) {
        includes.add(include.getClassName() + ".class");
        imports.add(include.getFQN());
    }

    public ProvidesVPModel getProvidesView() {
        return providesView;
    }

    public void setProvidesView(ProvidesVPModel providesView) {
        this.providesView = providesView;
        this.imports.add(providesView.getFQNApi().getFQN());
        this.imports.add(providesView.getFQNImpl().getFQN());
    }

    public ProvidesVPModel getProvidesPresenter() {
        return providesPresenter;
    }

    public void setProvidesPresenter(ProvidesVPModel providesPresenter) {
        this.providesPresenter = providesPresenter;
        this.imports.add(providesPresenter.getFQNApi().getFQN());
        this.imports.add(providesPresenter.getFQNImpl().getFQN());
    }

    public void addSlot(ProvidesSlotModel slotModel) {
        this.slots.add(slotModel);
        this.imports.add(slotModel.getFQNApi().getFQN());
        this.imports.add(slotModel.getFQNImpl().getFQN());
        this.imports.add(slotModel.getNestedSlot().getFQN());
    }

    public List<ProvidesSlotModel> getSlots() {
        return slots;
    }

    public boolean hasSlots() {
        return !slots.isEmpty();
    }

    public String getFQN() {
        return packageName + "." + className;
    }
}
