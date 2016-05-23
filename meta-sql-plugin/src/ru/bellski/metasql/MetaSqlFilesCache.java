package ru.bellski.metasql;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.sql.psi.SqlFile;
import com.intellij.sql.psi.SqlFileType;
import com.intellij.util.containers.HashMap;
import com.intellij.util.indexing.FileBasedIndex;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.lang.MetaSqlFile;

import java.util.Map;

/**
 * Created by Aleksandr on 13.05.2016.
 */
public class MetaSqlFilesCache {
    private final Map<SqlFile, MetaSqlFile> metaSqlFileBySqlFile = new HashMap<>();

    public MetaSqlFilesCache(@NotNull Project project) {
        FileBasedIndex
                .getInstance()
                .getContainingFiles(FileTypeIndex.NAME, SqlFileType.INSTANCE, GlobalSearchScope.allScope(project))
                .forEach(virtualFile -> {
                    final SqlFile sqlFile = (SqlFile) PsiManager.getInstance(project).findFile(virtualFile);


                });

    }

    public static MetaSqlFilesCache getInstance(Project project) {
        return ServiceManager.getService(project, MetaSqlFilesCache.class);
    }

    public MetaSqlFile findMetaSqlFile(SqlFile sqlFile) {
        return metaSqlFileBySqlFile.get(sqlFile);
    }
}
