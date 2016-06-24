package ru.bellski.metasql.lang.psi.impl;

import ru.bellski.metasql.lang.psi.MetaSqlCompositeElement;
import ru.bellski.metasql.lang.psi.MetaSqlReferenceElement;
import ru.bellski.metasql.lang.psi.MetaSqlTokenTypes;

/**
 * Created by oem on 6/20/16.
 */
public class MetaSqlPsiImplUtil {

    public static String getTypeName(MetaSqlReferenceElement element) {
        return element.getText();
    }

    public static String getName(MetaSqlCompositeElement element) {
        return element.getNode().findChildByType(MetaSqlTokenTypes.IDENTIFIER).getText();
    }
}
