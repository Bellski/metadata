package ru.bellski.metasql.util;

import com.intellij.openapi.project.Project;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.sql.psi.SqlFile;
import ru.bellski.metasql.lang.MetaSqlFile;
import ru.bellski.metasql.lang.psi.MetaSqlBody;
import ru.bellski.metasql.lang.psi.MetaSqlReturnRuleClause;
import ru.bellski.metasql.lang.psi.MetadataClause;
import ru.bellski.metasql.lang.psi.ReturnRuleClause;


/**
 * Created by oem on 5/18/16.
 */
public class MetaSqlUtils {
    public static ReturnRuleClause getReturnRuleClause(MetaSqlBody metaSqlBody) {
        return PsiTreeUtil.findChildOfType(metaSqlBody, ReturnRuleClause.class);
    }

    public static MetadataClause getMetadataClause(MetaSqlBody metaSqlBody) {
        return PsiTreeUtil.findChildOfType(metaSqlBody, MetadataClause.class);
    }
}
