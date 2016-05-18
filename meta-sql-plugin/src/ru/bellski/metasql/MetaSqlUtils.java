package ru.bellski.metasql;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiComment;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.sql.psi.SqlFile;

/**
 * Created by oem on 5/18/16.
 */
public class MetaSqlUtils {
    public static PsiClass findMetaClass(PsiComment possiblyCommentWithMetadata, Project project) {
        PsiClass psiClass = null;

        final String metadata =
                possiblyCommentWithMetadata
                        .getText()
                        .substring(2)
                        .trim();

        if (!metadata.isEmpty() && metadata.startsWith("metadata")) {
            psiClass = SqlMetadataJavaClassCache
                    .getInstance(project)
                    .findPsiClassByFqn(metadata.substring(8).trim());
        }

        return psiClass;
    }

    public static MetaSqlFile createMetaSqlFile(SqlFile sqlFile, Project project) {
        final PsiComment psiComment = PsiTreeUtil.findChildOfType(sqlFile, PsiComment.class);
        final MetaSqlFile metaSqlFile = new MetaSqlFile(sqlFile);

        if (psiComment != null) {
            final PsiClass psiClass = findMetaClass(psiComment, project);

            if (psiClass != null) {
                metaSqlFile.setMetaClass(psiClass, psiComment);
            }
        }

        return metaSqlFile;
    }
}
