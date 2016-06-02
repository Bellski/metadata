package ru.bellski.metasql.marker;

import com.intellij.codeHighlighting.Pass;
import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.FunctionUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import ru.bellski.metasql.generate.BetterGenerator;
import ru.bellski.metasql.generate.SqlQueryGenerator;
import ru.bellski.metasql.lang.psi.MetaSqlBody;

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
                            final MetaSqlBody metaSqlBody = PsiTreeUtil.findChildOfType(elt.getContainingFile(), MetaSqlBody.class);

                            final PsiLanguageInjectionHost injectedBlock = InjectedLanguageUtil.findInjectionHost(elt.getContainingFile());

                            final String queryName = injectedBlock.getContainingFile().getName().split("\\.sql")[0];

                            final PsiFile sqlFIle = injectedBlock.getContainingFile();

                            PsiElement clone = sqlFIle.copy();

                            ApplicationManager.getApplication().runWriteAction(() -> {
                                clone.deleteChildRange(clone.getFirstChild(), clone.getFirstChild());
                            });

                            final PsiClass psiClass = (PsiClass) metaSqlBody.getMetadataClause().getMetadataInit().getMetadataReference().resolve();

                            BetterGenerator
                                    .generate(
                                            queryName,
                                            psiClass.getQualifiedName().substring(0, psiClass.getQualifiedName().lastIndexOf(".")),
                                            clone.getText(),
                                            metaSqlBody,
                                            MavenProjectsManager
                                                    .getInstance(elt.getProject())
                                                    .findProject(ModuleUtil.findModuleForPsiElement(elt)),
                                            elt.getProject()
                                    );

//                            SqlQueryGenerator
//                                    .generate(
//                                            queryName,
//                                            "queries",
//                                            clone.getText(),
//                                            metaSqlBody,
//                                            MavenProjectsManager
//                                                    .getInstance(elt.getProject())
//                                                    .findProject(ModuleUtil.findModuleForPsiElement(elt)),
//                                            elt.getProject()
//                                    );

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
