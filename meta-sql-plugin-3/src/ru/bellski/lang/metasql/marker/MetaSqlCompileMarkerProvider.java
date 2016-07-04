package ru.bellski.lang.metasql.marker;

import com.intellij.codeHighlighting.Pass;
import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.util.FunctionUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bellski.lang.metasql.MetaSqlFile;
import ru.bellski.lang.metasql.generator.MetaQueryGenerator;
import ru.bellski.lang.metasql.generator.QueryExecutorGenerator;
import ru.bellski.lang.metasql.generator.StepInterfaceGenerator;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by Aleksandr on 26.06.2016.
 */
public class MetaSqlCompileMarkerProvider implements LineMarkerProvider {
    private static final String DEFAULT_QUERY_PATH = "sql/queries";

    public static class MetaSqlCompileLineMarkerInfo extends LineMarkerInfo<PsiElement> {

        public MetaSqlCompileLineMarkerInfo(PsiElement element) {
            super(element, element.getTextRange(), AllIcons.Actions.Compile, Pass.UPDATE_ALL, FunctionUtil.constant("Generate"), (e, elt) -> {
                final MetaSqlFile metaSqlFile = (MetaSqlFile) elt.getContainingFile();
                final PsiManager psiManager = PsiManager.getInstance(metaSqlFile.getProject());

                if (!metaSqlFile.isHasErrors()) {
                    final Module module = ModuleUtil.findModuleForFile(metaSqlFile.getVirtualFile(), metaSqlFile.getProject());

                    final PsiClass queryExecutorClass = QueryExecutorGenerator.generate(metaSqlFile);
                    writeJavaFile(psiManager, metaSqlFile, queryExecutorClass, module);

                    final PsiClass[] stepInterfaces = StepInterfaceGenerator.generate(metaSqlFile, queryExecutorClass);
                    writeJavaFiles(psiManager, metaSqlFile, stepInterfaces, module);

                    final PsiClass queryClass = MetaQueryGenerator
                            .generate(metaSqlFile, stepInterfaces, queryExecutorClass);
                    writeJavaFile(psiManager, metaSqlFile, queryClass, module);

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

    private static void writeJavaFiles(PsiManager psiManager, MetaSqlFile metaSqlFile, PsiClass[] classesToWrite, Module module) {
        for (PsiClass psiClass : classesToWrite) {
            writeJavaFile(psiManager, metaSqlFile, psiClass, module);
        }
    }

    private static void writeJavaFile(PsiManager psiManager, MetaSqlFile metaSqlFile, PsiClass classToWrite, Module module) {
        final Project project = metaSqlFile.getProject();

        final String path = module.getModuleFile().getParent().getPath().concat("/src/main/java/").concat(metaSqlFile.getPackagePath());

        ApplicationManager.getApplication().runWriteAction(() -> {
            VirtualFile queryPath = project.getBaseDir().findFileByRelativePath(path);

            if (queryPath == null) {
                try {
                    queryPath = VfsUtil.createDirectories(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                final PsiDirectory queriesDirectory = psiManager.findDirectory(queryPath);

                deleteJavaFileIfExists(queriesDirectory, classToWrite.getName() + ".java");

                queriesDirectory.add(classToWrite);
            }
        });
    }

    private static void deleteJavaFileIfExists(PsiDirectory queriesDirectory, String className) {
        final PsiFile foundedFile = queriesDirectory.findFile(className);

        if (foundedFile != null) {
            foundedFile.delete();
        }
    }
}
