package org.vaadin.rise.codegen.freemaker;

import com.sun.tools.javac.code.Symbol;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;

/**
 * Created by Aleksandr on 16.07.2016.
 */
public class ElementsUtils {
    public static FQN toFQN(Elements elements, Element element) {
        final Symbol elementSymbol = Symbol.class.cast(element);

        final String packageName = elementSymbol.packge().getQualifiedName().toString();
        final String fqn = elementSymbol.getQualifiedName().toString();

        return FQN.create(fqn.substring(packageName.length() + 1), packageName);
    }

    public static FQN toRiseFQN(Elements elements, Element element) {
        return new FQN("Rise" + element.getSimpleName().toString(), elements.getPackageOf(element).getQualifiedName().toString());
    }
}


