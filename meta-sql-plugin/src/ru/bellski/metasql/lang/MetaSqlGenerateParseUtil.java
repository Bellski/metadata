package ru.bellski.metasql.lang;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;

/**
 * Created by Aleksandr on 23.05.2016.
 */
public class MetaSqlGenerateParseUtil extends GeneratedParserUtilBase {
    public static boolean nonStrictID(PsiBuilder builder_, int level_) {
        final PsiBuilder.Marker marker_ = builder_.mark();
        return true;
    }
}
