package ru.bellski.metasql.lang.marker;

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
import ru.bellski.metasql.lang.generator.MetaSqlGenerator;

import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.List;

/**
 * Created by Aleksandr on 22.05.2016.
 */
public class MetaSqlRunMarkerProvider implements LineMarkerProvider {

    public class RunLineMarkerInfo extends  LineMarkerInfo<PsiElement> {
        public RunLineMarkerInfo(PsiElement element) {
            super(
                    element,
                    element.getTextRange(),
                    AllIcons.Actions.Compile,
                    Pass.UPDATE_ALL,
                    FunctionUtil.constant("Generate"),
                    new GutterIconNavigationHandler<PsiElement>() {
                        @Override
                        public void navigate(MouseEvent e, PsiElement elt) {
                            System.out.println(MetaSqlGenerator.generate());
                        }
                    },
                    GutterIconRenderer.Alignment.RIGHT
            );
        }
    }

    @Nullable
    @Override
    public LineMarkerInfo getLineMarkerInfo(@NotNull PsiElement element) {
        if (element.getContainingFile().getFirstChild().equals(element)) {
            return new RunLineMarkerInfo(element);
        }
        return null;
    }

    @Override
    public void collectSlowLineMarkers(@NotNull List<PsiElement> elements, @NotNull Collection<LineMarkerInfo> result) {

    }
}
