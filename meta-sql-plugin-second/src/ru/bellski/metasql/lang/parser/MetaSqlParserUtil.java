package ru.bellski.metasql.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;

/**
 * Created by oem on 6/6/16.
 */
public class MetaSqlParserUtil extends GeneratedParserUtilBase {
    public static boolean parseGrammar(PsiBuilder builder_, int level, Parser parser) {
        ErrorState state = ErrorState.get(builder_);
        return parseAsTree(state, builder_, level, DUMMY_BLOCK, true, parser, TRUE_CONDITION);
    }
}
