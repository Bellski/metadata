<#-- @ftlvariable name="" type="org.vaadin.rise.plugin.idea.model.RiseModuleModel" -->
package ${packageName};

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ${name} {

    @Provides
    @Singleton
    ${riseViewModel.apiName}.View providesApplicationView(${riseViewModel.name} view) {
        return view;
    }

    @Provides
    @Singleton
    ${presenterModel.apiName}.Presenter providesApplicationPresenter(${presenterModel.name} presenter) {
        return presenter;
    }
}