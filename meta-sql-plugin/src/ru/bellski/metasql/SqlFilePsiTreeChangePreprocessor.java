package ru.bellski.metasql;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.PsiTreeChangeEventImpl;
import com.intellij.psi.impl.PsiTreeChangePreprocessor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.sql.psi.SqlFile;
import org.jetbrains.annotations.NotNull;

/**
 * Created by oem on 5/18/16.
 */
public class SqlFilePsiTreeChangePreprocessor implements PsiTreeChangePreprocessor {
    private final Project project;

    public SqlFilePsiTreeChangePreprocessor(@NotNull Project project) {
        this.project = project;
    }

    @Override
    public void treeChanged(@NotNull PsiTreeChangeEventImpl event) {
        if (event.getFile() instanceof SqlFile) {
            switch (event.getCode()) {
                case CHILD_ADDED:
                case CHILD_REMOVED:
                case CHILD_REPLACED:
                    processChanges(event.getOldChild(), event.getNewChild(), (SqlFile) event.getFile());
                    break;
            }
        }
    }

    private void processChanges(PsiElement oldChild, PsiElement newChild, SqlFile file) {
        final MetaSqlFile metaSqlFile = MetaSqlFilesCache.getInstance(project).findMetaSqlFile(file);

        if (newChild instanceof PsiComment) {
            final PsiComment possiblyCommentWithMetadata = PsiTreeUtil.findChildOfType(file, PsiComment.class);

            if (newChild.equals(possiblyCommentWithMetadata)) {
                if (newChild.getText().startsWith("-- metadata")) {
                    metaSqlFile.setMetaClass(MetaSqlUtils.findMetaClass(possiblyCommentWithMetadata,project), possiblyCommentWithMetadata);
                }
            } else {

            }
        }
    }
}
