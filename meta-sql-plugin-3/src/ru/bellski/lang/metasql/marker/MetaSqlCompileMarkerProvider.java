package ru.bellski.lang.metasql.marker;

import com.intellij.codeHighlighting.Pass;
import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.psi.PsiElement;
import com.intellij.util.FunctionUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.List;

/**
 * Created by Aleksandr on 26.06.2016.
 */
public class MetaSqlCompileMarkerProvider implements LineMarkerProvider {
    public static class MetaSqlCompileLineMarkerInfo extends LineMarkerInfo<PsiElement> {

        public MetaSqlCompileLineMarkerInfo(PsiElement element) {
            super(element, element.getTextRange(), AllIcons.Actions.Compile, Pass.UPDATE_ALL, FunctionUtil.constant("Generate"), new GutterIconNavigationHandler<PsiElement>() {
                @Override
                public void navigate(MouseEvent e, PsiElement elt) {

                }
            }, GutterIconRenderer.Alignment.RIGHT);
        }
    }

    @Nullable
    @Override
    public LineMarkerInfo getLineMarkerInfo(@NotNull PsiElement element) {
        if (element.getContainingFile().getFirstChild().equals(element)) {
            return new MetaSqlCompileLineMarkerInfo(element);
        }
        return null;
    }

    @Override
    public void collectSlowLineMarkers(@NotNull List<PsiElement> elements, @NotNull Collection<LineMarkerInfo> result) {

    }
}
