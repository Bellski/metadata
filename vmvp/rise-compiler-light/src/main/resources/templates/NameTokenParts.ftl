<#-- @ftlvariable name="" type="org.vaadin.rise.codegen.light.model.NameTokenPartsModel" -->
package ${packageName};

import java.util.HashSet;
import java.util.Set;

public class ${className} {
public static Set<String> parts = new HashSet<>(); static {
<#list placeNames as placeName>
    parts.add("${placeName}");
</#list>
    }
}