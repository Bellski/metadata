<#-- @ftlvariable name="" type="org.vaadin.rise.codegen.model.EntryComponentModel" -->

package ${packageName};

import dagger.Component;
import org.vaadin.rise.vaadin.VaadinModule;

<#list importList as importName>
import ${importName};
</#list>

import javax.inject.Singleton;

@Singleton
@Component(modules = {RiseBootstrapModule.class, ${entryModuleModel.className}.class})
public interface ${className} {
    Bootstrap bootstrap();
}