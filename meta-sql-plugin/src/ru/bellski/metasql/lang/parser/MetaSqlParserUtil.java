package ru.bellski.metasql.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.psi.tree.IElementType;
import ru.bellski.metasql.lang.psi.MetaSqlTypes;

/**
 * Created by oem on 5/19/16.
 */
public class MetaSqlParserUtil extends GeneratedParserUtilBase {
    public static boolean lastParam(PsiBuilder builder, int level) {
        IElementType currentToken = builder.getTokenType();

        return !(nextTokenIs(builder, MetaSqlTypes.RBRACKET));

    }
}
