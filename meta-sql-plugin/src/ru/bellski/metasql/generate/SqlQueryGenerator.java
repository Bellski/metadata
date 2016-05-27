package ru.bellski.metasql.generate;

import com.intellij.codeInsight.actions.ReformatCodeProcessor;
import com.intellij.codeInsight.template.ExpressionUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.PsiClassImplUtil;
import com.intellij.psi.impl.PsiJavaParserFacadeImpl;
import com.intellij.psi.impl.PsiManagerImpl;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.psi.impl.source.PsiClassInitializerImpl;
import com.intellij.psi.impl.source.tree.JavaSharedImplUtil;
import com.intellij.psi.impl.source.tree.java.PsiBinaryExpressionImpl;
import com.intellij.psi.impl.source.tree.java.PsiLiteralExpressionImpl;
import com.intellij.psi.impl.source.tree.java.PsiPolyadicExpressionImpl;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiClassUtil;
import com.intellij.psi.util.PsiConcatenationUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtil;
import com.intellij.spring.model.utils.PsiTypeUtil;
import com.intellij.util.text.LiteralFormatUtil;
import com.sixrr.rpp.utils.PsiUtils;
import com.siyeh.ig.psiutils.ExpressionUtils;
import org.jetbrains.idea.maven.project.MavenProject;
import ru.bellski.metasql.lang.psi.MetaSqlBody;
import ru.bellski.metasql.lang.psi.MetaSqlParameter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by oem on 5/26/16.
 */
public class SqlQueryGenerator {
    public static void generate(String name, String sqlQuery, MetaSqlBody metaSqlBody, MavenProject mProject, Project project) {
        String genDir = mProject.getGeneratedSourcesDirectory(false);
        PsiElementFactory elemFactory = ServiceManager.getService(project, PsiElementFactory.class);


        try {
            final VirtualFile queriesDir = VfsUtil.createDirectories(genDir + "/meta/queries");
            final PsiClass queryClass = JavaDirectoryService
                    .getInstance()
                    .createClass(new PsiDirectoryImpl((PsiManagerImpl) PsiManager.getInstance(project), queriesDir), name);

            ApplicationManager.getApplication().runWriteAction(new Runnable() {
                @Override
                public void run() {
                    final Collection<MetaSqlParameter> parameters = PsiTreeUtil.findChildrenOfType(metaSqlBody, MetaSqlParameter.class);

                    final PsiField parametersField = elemFactory
                            .createField(
                                    "parameters",
                                    PsiClassType.getJavaLangObject(PsiManager.getInstance(project), GlobalSearchScope.allScope(project)).createArrayType()
                            );

                    parametersField.setInitializer(elemFactory.createExpressionFromText("new Object["+parameters.size()+ "]", null));

                    queryClass.add(parametersField);

                    final PsiField queryField = elemFactory
                            .createField(
                                    "query",
                                    PsiClassType.getJavaLangString(PsiManager.getInstance(project), GlobalSearchScope.allScope(project))
                            );



                    String[] strings = StringUtil.splitByLinesKeepSeparators(sqlQuery);


                    for (int i = 0; i < strings.length; i++) {
                        strings[i] = StringUtil.escapeLineBreak(strings[i]);
                        strings[i]= StringUtil.wrapWithDoubleQuote(strings[i]);
                    }


                    queryField.setInitializer(elemFactory.createExpressionFromText(StringUtil.join(strings, "+"), null));


                    queryClass.add(queryField);



                    new ReformatCodeProcessor(queryClass.getContainingFile(), true).preprocessFile(queryClass.getContainingFile(), true);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
