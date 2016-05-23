package ru.bellski.metasql.util;

import com.intellij.openapi.project.Project;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import com.intellij.sql.psi.SqlFile;
import ru.bellski.metasql.lang.MetaSqlFile;


/**
 * Created by oem on 5/18/16.
 */
public class MetaSqlUtils {

    public static MetaSqlFile findInjectedMetaSql(SqlFile sqlFile) {
        return InjectedLanguageUtil.findInjectedFile(sqlFile.getChildren()[0], MetaSqlFile.class);
    }
}
