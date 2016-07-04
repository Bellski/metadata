package ru.bellski.metasql;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.XmlHighlighterColors;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

/**
 * Created by oem on 5/13/16.
 */
public class MetaSqlAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder holder) {
        if (psiElement instanceof PsiComment) {
            MetaSqlFilesCache.getInstance(psiElement.getProject());

            final PsiFile sqlFile = psiElement.getContainingFile();

            final PsiElement commentCandidate = sqlFile.getChildren()[0];

            if (commentCandidate.equals(psiElement)) {
                String value = commentCandidate.getText().substring(2).trim();

                if (!value.isEmpty() && value.startsWith("metadata")) {
                    TextRange range = new TextRange(commentCandidate.getText().indexOf('m'), value.length() + commentCandidate.getText().indexOf('m'));
                    Annotation annotation = holder.createInfoAnnotation(range, "metadata");
                    annotation.setTextAttributes(XmlHighlighterColors.XML_TAG);
                }
            }
        }
    }
}
