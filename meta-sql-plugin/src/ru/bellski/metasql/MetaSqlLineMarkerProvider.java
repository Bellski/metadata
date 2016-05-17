package ru.bellski.metasql;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.sql.psi.SqlFile;
import com.intellij.sql.psi.impl.SqlStringTokenElement;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * Created by Aleksandr on 15.05.2016.
 */
public class MetaSqlLineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
        if (element instanceof SqlStringTokenElement) {
            if (element.getContainingFile() instanceof SqlFile) {
                final MetaSqlFile metaSqlFile = MetaSqlFilesCache
                        .getInstance(element.getProject())
                        .findMetaSqlFile((SqlFile) element.getContainingFile());

                final PsiField metaField = metaSqlFile.findMetaFieldByValue(((SqlStringTokenElement) element).getTokenText());

                if (metaField != null) {
                    NavigationGutterIconBuilder<PsiElement> builder =
                            NavigationGutterIconBuilder
                                    .create(AllIcons.FileTypes.JavaClass).setTarget(metaField);

                    result.add(builder.createLineMarkerInfo(element));
                }
            }
        }
    }
}
