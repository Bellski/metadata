package org.vaadin.rise.codegen.model;

import com.sun.tools.javac.code.Symbol;
import org.vaadin.rise.core.annotation.RiseModule;

import javax.lang.model.element.Element;
import javax.lang.model.util.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.vaadin.rise.core.annotation.RiseModuleHelper.*;

/**
 * Created by Aleksandr on 16.07.2016.
 */
public class ModuleModel extends JFOModel {
    private final FqnHolder extendsModule;
    private final Element extendsModuleElement;

    private List<ModuleModel> includes = new ArrayList<>(2);
    private List<DaggerProvidesMethodModel> slots = new ArrayList<>(2);
    private DaggerProvidesMethodModel daggerProvidesProxyMethodModel;

    private DaggerProvidesMethodModel daggerProvidesMethodViewModel;
    private DaggerProvidesMethodModel daggerProvidesMethodPresenterModel;


    public ModuleModel(String className, String packageName, Element extendsModuleElement) {
        super(className, className, packageName);
        this.extendsModuleElement = extendsModuleElement;
        this.extendsModule = new FqnHolder
            (
                extendsModuleElement.getSimpleName().toString(),
                extendsModuleElement.getSimpleName().toString(),
                Symbol.class.cast(extendsModuleElement).packge().toString()
            );
        addImport(extendsModule);
    }

    public FqnHolder getExtendsModule() {
        return extendsModule;
    }

    public Element getExtendsModuleElement() {
        return extendsModuleElement;
    }

    public boolean hasIncludes() {
        return !includes.isEmpty();
    }

    public List<ModuleModel> getIncludes() {
        return includes;
    }

    public List<DaggerProvidesMethodModel> getSlots() {
        return slots;
    }

    public String getJoinedIncludes() {
        return includes
                .stream()
                .map(fqnHolder -> {
					addImport(fqnHolder);
					return fqnHolder.getClassName() + ".class";
				})
                .collect(Collectors.joining(","));
    }

    public void include(List<ModuleModel> children) {
        for (ModuleModel child : children) {
            includes.add(child);
            addImport(child);
        }
    }

    public DaggerProvidesMethodModel getDaggerProvidesMethodViewModel() {
        return daggerProvidesMethodViewModel;
    }

    public void setDaggerProvidesMethodViewModel(DaggerProvidesMethodModel daggerProvidesMethodViewModel) {
        this.daggerProvidesMethodViewModel = daggerProvidesMethodViewModel;
        addImport(daggerProvidesMethodViewModel.getProvidesImpl());
        addImport(daggerProvidesMethodViewModel.getProvidesInterface());
    }

    public DaggerProvidesMethodModel getDaggerProvidesMethodPresenterModel() {
        return daggerProvidesMethodPresenterModel;
    }

    public void setDaggerProvidesMethodPresenterModel(DaggerProvidesMethodModel daggerProvidesMethodPresenterModel) {
        this.daggerProvidesMethodPresenterModel = daggerProvidesMethodPresenterModel;
        addImport(daggerProvidesMethodPresenterModel.getProvidesImpl());
        addImport(daggerProvidesMethodPresenterModel.getProvidesInterface());
    }

    public void addSlot(DaggerProvidesMethodModel daggerProvidesSlotMethodModel) {
        slots.add(daggerProvidesSlotMethodModel);
        addImport(daggerProvidesSlotMethodModel.getProvidesImpl());
        addImport(daggerProvidesSlotMethodModel.getProvidesInterface());
    }



    public boolean hasSlots() {
        return !slots.isEmpty();
    }

    public DaggerProvidesMethodModel getDaggerProvidesProxyMethodModel() {
        return daggerProvidesProxyMethodModel;
    }

    public void setDaggerProvidesProxyMethodModel(DaggerProvidesMethodModel daggerProvidesProxyMethodModel) {
        this.daggerProvidesProxyMethodModel = daggerProvidesProxyMethodModel;
        addImport(daggerProvidesProxyMethodModel.getProvidesImpl());
        addImport(daggerProvidesProxyMethodModel.getProvidesInterface());
    }

    public static ModuleModel create(Types elements, Map<FqnHolder, List<NestedSlotModel>> slotGraph, Map<FqnHolder, ProxyModel> proxyModels, Element moduleElement) {
        final String moduleName = moduleElement.getSimpleName().toString();
        final String modulePackage = Symbol.class.cast(moduleElement).packge().toString();

        RiseModule riseModuleAnn = moduleElement.getAnnotation(RiseModule.class);

        final ModuleModel moduleModel = new ModuleModel
            (
                "Rise" + moduleName,
                modulePackage,
                moduleElement
            );


        /*
                        PROVIDES VIEW, PRESENTER
         */
        final Element viewElem = elements.asElement(viewValue(riseModuleAnn));
        final Element viewApiElem = elements.asElement(viewApiValue(riseModuleAnn));

        final DaggerProvidesMethodModel daggerProvidesMethodViewModel = new DaggerProvidesMethodModel
            (
                viewElem.getSimpleName().toString(),
                buildJavaCompatibleFQN(viewElem),
                buildJavaCompatibleFQN(viewApiElem)
            );


        moduleModel.setDaggerProvidesMethodViewModel(daggerProvidesMethodViewModel);

        final Element presenterElem = elements.asElement(presenterValue(riseModuleAnn));
        final Element presenterApiElem = elements.asElement(presenterApiValue(riseModuleAnn));

        final FqnHolder presenterImpl = buildJavaCompatibleFQN(presenterElem);

        final DaggerProvidesMethodModel daggerProvidesMethodPresenterModel = new DaggerProvidesMethodModel
            (
                presenterElem.getSimpleName().toString(),
                presenterImpl,
                buildJavaCompatibleFQN(presenterApiElem)
            );

        moduleModel.setDaggerProvidesMethodPresenterModel(daggerProvidesMethodPresenterModel);

        /*
                       PROVIDES SLOTS
         */

        final List<NestedSlotModel> slots = slotGraph.get(presenterImpl);

        if (slots != null) {
            for (NestedSlotModel slot : slots) {
                moduleModel.addSlot(
                    new DaggerProvidesMethodModel
                        (
                            slot.getClassName(),
                            slot.getExtendsSlot(),
                            slot.getSlotOwner()
                        )
                );
            }
        }

        /*
                     PROVIDES PROXY
         */

        final ProxyModel proxy = proxyModels.get(presenterImpl);

        moduleModel.setDaggerProvidesProxyMethodModel(
            new DaggerProvidesMethodModel
                (
                    proxy.getClassName(),
                    proxy,
                    proxy.getProxyTarget()
                )
        );

        return moduleModel;
    }
}
