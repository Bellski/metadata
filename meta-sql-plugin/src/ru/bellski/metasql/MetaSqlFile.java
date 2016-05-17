package ru.bellski.metasql;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.sql.psi.*;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by Aleksandr on 13.05.2016.
 */
public class MetaSqlFile {
    private final SqlFile sqlFile;
    private PsiClass metaClass;
    private PsiComment commentWithMetadata;
    private Map<String, PsiField> metaFieldByValue;
    private List<String> metaValues;

    public MetaSqlFile(SqlFile sqlFile) {
        this.sqlFile = sqlFile;

        metaClass = findMetaClass(sqlFile.getFirstChild());

        PsiTreeUtil.findChildrenOfType(sqlFile, SqlParameter.class).forEach(new Consumer<SqlParameter>() {
            @Override
            public void accept(SqlParameter sqlParameter) {
                System.out.println(sqlParameter.getText());
            }
        });

        if (metaClass != null) {
            metaFieldByValue = collectMetaFields();
        }
    }

    private PsiClass findMetaClass(PsiElement possiblyCommentWithMetadata) {
        PsiClass psiClass = null;

        if (possiblyCommentWithMetadata instanceof PsiComment) {
            this.commentWithMetadata = (PsiComment) possiblyCommentWithMetadata;

            final String metadata =
                    possiblyCommentWithMetadata
                            .getText()
                            .substring(2)
                            .trim();

            if (!metadata.isEmpty() && metadata.startsWith("metadata")) {
                psiClass = SqlMetadataJavaClassCache
                        .getInstance(sqlFile.getProject())
                        .findPsiClassByFqn(metadata.substring(8).trim());
            }
        }

        return psiClass;
    }

    private Map<String, PsiField> collectMetaFields() {
        final Map<String, PsiField> metaFields = new HashMap<>();
        this.metaValues = new ArrayList<>();

        for (PsiField psiField : metaClass.getFields()) {
            final String value = psiField
                    .getInitializer()
                    .getText()
                    .replaceAll("\"", "");

            metaFields.put(value,psiField);
            this.metaValues.add(value);
        }

        return metaFields;
    }

    public PsiClass getMetaClass() {
        return metaClass;
    }

    public Collection<PsiField> getMetaFields() {
        return metaFieldByValue.values();
    }

    public List<String> getMetaValues() {
        return metaValues;
    }

    public PsiField findMetaFieldByValue(String metaValue) {
        if (metaFieldByValue == null) {
            return null;
        }
        return metaFieldByValue.get(metaValue);
    }

    public boolean commentWithMetadataEquals(PsiElement psiElement) {
        return psiElement.getText().substring(2).trim().startsWith("metadata");
    }
}
