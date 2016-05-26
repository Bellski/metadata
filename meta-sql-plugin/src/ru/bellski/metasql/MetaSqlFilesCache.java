package ru.bellski.metasql;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.event.DocumentAdapter;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.sql.psi.SqlFile;
import com.intellij.util.containers.HashMap;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.lang.MetaSqlFile;

import java.util.Map;

/**
 * Created by Aleksandr on 13.05.2016.
 */
public class MetaSqlFilesCache implements PsiDocumentManager.Listener {
    private final Map<SqlFile, MetaSqlFile> metaSqlFileBySqlFile = new HashMap<>();

    public MetaSqlFilesCache(@NotNull Project project) {

    }

    public static MetaSqlFilesCache getInstance(Project project) {
        return ServiceManager.getService(project, MetaSqlFilesCache.class);
    }

    public MetaSqlFile findMetaSqlFile(SqlFile sqlFile) {
        return metaSqlFileBySqlFile.get(sqlFile);
    }

    @Override
    public void documentCreated(@NotNull Document document, PsiFile psiFile) {
        psiFile.subtreeChanged();
    }

    @Override
    public void fileCreated(@NotNull PsiFile file, @NotNull Document document) {

    }
}
