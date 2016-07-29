package org.vaadin.rise.codegen.model;

import com.sun.tools.javac.code.Symbol;
import org.vaadin.rise.core.annotation.NoGateKeeper;
import org.vaadin.rise.core.annotation.Presenter;
import org.vaadin.rise.core.annotation.RiseModule;
import org.vaadin.rise.core.annotation.RiseModuleHelper;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.vaadin.rise.core.annotation.RiseModuleHelper.*;

/**
 * Created by Aleksandr on 16.07.2016.
 */
public class ModuleModel extends JFOModel {
    private final FqnHolder extendsModule;
    private final Element extendsModuleElement;

    private final List<ModuleModel> includes = new ArrayList<>(2);
    private final List<DaggerProvidesMethodModel> slots = new ArrayList<>(2);
    private ProvidesPlaceMethod daggerProvidesPlaceMethodModel;

    private DaggerProvidesMethodModel daggerProvidesMethodViewModel;
    private DaggerProvidesMethodModel daggerProvidesMethodPresenterModel;

    private final List<ParameterModel> constructorParameters = new ArrayList<>(2);
    private FqnHolder gateKeeper;


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

    public ProvidesPlaceMethod getDaggerProvidesPlaceMethodModel() {
        return daggerProvidesPlaceMethodModel;
    }

    public boolean hasPlace() {
        return daggerProvidesPlaceMethodModel != null;
    }


    public void setDaggerProvidesPlaceMethodModel(ProvidesPlaceMethod daggerProvidesPlaceMethodModel) {
        this.daggerProvidesPlaceMethodModel = daggerProvidesPlaceMethodModel;
    }

    public String getJoinedConstructorParameters() {
        return constructorParameters
            .stream()
            .map(parameterModel -> parameterModel.getFqnHolder().getClassName() + " " +parameterModel.getName())
            .collect(Collectors.joining(","));

    }

    public String getJoinedConstructorParameterNames() {
        return constructorParameters
            .stream()
            .map(ParameterModel::getName)
            .collect(Collectors.joining(","));
    }

    public boolean hasConstructor() {
        return !constructorParameters.isEmpty();
    }

    public void addConstructorParameter(ParameterModel parameterModel) {
        constructorParameters.add(parameterModel);
        addImport(parameterModel.getFqnHolder());
    }


    public void setGateKeeper(FqnHolder gateKeeper) {
        this.gateKeeper = gateKeeper;
    }

    public FqnHolder getGateKeeper() {
        return gateKeeper;
    }

    public static ModuleModel create(Elements elements, Types types, Map<FqnHolder, List<NestedSlotModel>> slotGraph, Element moduleElement, Element defaultGateKeeper) {
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
        final Element viewElem = types.asElement(viewValue(riseModuleAnn));
        final Element viewApiElem = types.asElement(viewApiValue(riseModuleAnn));

        final DaggerProvidesMethodModel daggerProvidesMethodViewModel = new DaggerProvidesMethodModel
            (
                viewElem.getSimpleName().toString(),
                buildJavaCompatibleFQN(viewElem),
                buildJavaCompatibleFQN(viewApiElem)
            );


        moduleModel.setDaggerProvidesMethodViewModel(daggerProvidesMethodViewModel);

        final Element presenterElem = types.asElement(presenterValue(riseModuleAnn));
        final Element presenterApiElem = types.asElement(presenterApiValue(riseModuleAnn));

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
                     PROVIDES PLACE
         */

        final Presenter presenterAnnotation = presenterElem.getAnnotation(Presenter.class);

        if (!presenterAnnotation.placeName().isEmpty() && !presenterAnnotation.placeBus()) {
            moduleModel.setDaggerProvidesPlaceMethodModel(new ProvidesPlaceMethod(presenterAnnotation.placeName(), FqnHolder.buildJavaCompatibleFQN(presenterElem)));

            final TypeMirror NO_GATEKEEPER = elements.getTypeElement(NoGateKeeper.class.getName()).asType();
            final TypeMirror gateKeeperType = RiseModuleHelper.gateKeeperValue(presenterAnnotation);

            if (!presenterAnnotation.authorizePlace()) {
                if (!types.isSameType(gateKeeperType, NO_GATEKEEPER)) {
                    moduleModel.setGateKeeper(FqnHolder.buildJavaCompatibleFQN(types.asElement(gateKeeperType)));
                }
            }
        }


        final Optional<? extends Element> optionalConstructor = moduleElement
            .getEnclosedElements()
            .stream()
            .filter(enclosedElement -> Symbol.class.cast(enclosedElement).isConstructor())
            .findFirst();

        if (optionalConstructor.isPresent()) {
            final Symbol.MethodSymbol constructorElement = (Symbol.MethodSymbol) optionalConstructor.get();
            for (Symbol.VarSymbol varSymbol : constructorElement.getParameters()) {
                final String typeName = varSymbol.type.asElement().getSimpleName().toString();
                final String typePackage = varSymbol.type.asElement().packge().toString();

                final String parameterName = varSymbol.name.toString();

                moduleModel.addConstructorParameter(new ParameterModel(parameterName, new FqnHolder(typeName, typeName, typePackage)));
            }
        }

        return moduleModel;
    }

}
