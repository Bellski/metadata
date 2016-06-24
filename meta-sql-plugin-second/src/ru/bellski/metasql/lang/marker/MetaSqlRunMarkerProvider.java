package ru.bellski.metasql.lang.marker;

import com.intellij.codeHighlighting.Pass;
import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.icons.AllIcons;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiManager;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.FunctionUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bellski.metasql.lang.MetaSqlFile;
import ru.bellski.metasql.lang.generator.builder.MetaQueryBuilder;
import ru.bellski.metasql.lang.psi.*;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by Aleksandr on 22.05.2016.
 */
public class MetaSqlRunMarkerProvider implements LineMarkerProvider {

    public class RunLineMarkerInfo extends LineMarkerInfo<PsiElement> {
        public RunLineMarkerInfo(PsiElement element) {
            super(
                    element,
                    element.getTextRange(),
                    AllIcons.Actions.Compile,
                    Pass.UPDATE_ALL,
                    FunctionUtil.constant("Generate"),
                    new GutterIconNavigationHandler<PsiElement>() {
                        private Project project;

                        @Override
                        public void navigate(MouseEvent e, PsiElement elt) {
                            final MetaSqlFile metaSqlFile = (MetaSqlFile) elt.getContainingFile();

                            this.project = elt.getProject();

                            final Module module = ModuleUtil.findModuleForFile(metaSqlFile.getVirtualFile(), project);

                            final Collection<MetaSqlParameterDefinition> parameters
                                    = PsiTreeUtil.findChildrenOfType(metaSqlFile, MetaSqlParameterDefinition.class);

                            final MetaSqlPackageDefinition packageDef
                                    = PsiTreeUtil.findChildOfType(metaSqlFile, MetaSqlPackageDefinition.class);

//                            final MetaQueryBuilder metaQueryBuilder = new MetaQueryBuilder(
//                                    "Test",
//                                    "Query",
//                                    PsiTreeUtil.findChildOfAnyType(metaSqlFile, MetaSqlRoot.class)
//                            );

                            String path = module.getModuleFile().getParent().getPath().concat("/target/generated-sources/meta/");

//                            saveJavaFile(metaQueryBuilder, packageDef == null ? path.concat("queries") : path.concat(packageDef.getPackageName().getText().replace(".", "/")));
                        }

                        private void saveJavaFile(MetaQueryBuilder metaQueryBuilder, String path) {
                            final PsiFileFactory psiFileFactory = PsiFileFactory.getInstance(project);
                            final PsiManager psiManager = PsiManager.getInstance(project);

                            ApplicationManager.getApplication().runWriteAction(() -> {
                                try {
                                    VirtualFile metaSqlPackage = project
                                            .getBaseDir()
                                            .findFileByRelativePath(
                                                    path
                                            );

                                    if (metaSqlPackage == null) {
                                        metaSqlPackage = VfsUtil.createDirectories(path);
                                    }

                                    final PsiDirectory directory = psiManager.findDirectory(metaSqlPackage);
                                    directory.add(
                                            psiFileFactory
                                                    .createFileFromText(
                                                            metaQueryBuilder.getName()+".java",
                                                            JavaFileType.INSTANCE,
                                                            metaQueryBuilder.toString()
                                                    )
                                    );

                                    directory.add(
                                            psiFileFactory
                                                    .createFileFromText(
                                                            metaQueryBuilder.getExecutorBuilder().getName()+".java",
                                                            JavaFileType.INSTANCE,
                                                            metaQueryBuilder.getExecutorBuilder().toString()
                                                    )
                                    );

                                    metaQueryBuilder.getStepBuilders().forEach(stepBuilder -> directory.add(
                                            psiFileFactory
                                                    .createFileFromText(
                                                            stepBuilder.getName()+".java",
                                                            JavaFileType.INSTANCE,
                                                            stepBuilder.toString()
                                                    )
                                    ));

                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });

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
