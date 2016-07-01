package ru.bellski.lang.metasql.generator;

import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import ru.bellski.lang.metasql.MetaSqlFile;
import ru.bellski.lang.metasql.psi.MetaSqlCodeReferenceElement;

/**
 * Created by oem on 6/29/16.
 */
public class QueryExecutorGenerator {
    public static PsiClass generate(MetaSqlFile metaSqlFile) {
        final Project project = metaSqlFile.getProject();
        final PsiElementFactory elementFactory = PsiElementFactory.SERVICE.getInstance(project);
        final JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(project);

        final PsiClass executorInterface = elementFactory.createInterface(metaSqlFile.getQueryName() + "Executor");
        final PsiJavaFile executorFile = (PsiJavaFile) executorInterface.getContainingFile();

        executorInterface.add(createExecutionMethod(elementFactory, executorFile.getImportList(), metaSqlFile));
        executorInterface.add(createExecutionMethodWithConnectionParameter(elementFactory, javaPsiFacade, executorFile.getImportList(), metaSqlFile));

        return executorInterface;
    }

    public static PsiPackageStatement createPackageStatement(PsiElementFactory elementFactory, String packageName) {
        return elementFactory.createPackageStatement(packageName);
    }

    public static PsiMethod createExecutionMethod(PsiElementFactory elementFactory, PsiImportList importList, MetaSqlFile metaSqlFile) {
        final MetaSqlCodeReferenceElement returnType = metaSqlFile.getReturnType();
        String type = null;

        if (returnType != null) {
            importList.add(elementFactory.createImportStatement((PsiClass) returnType.resolve()));
            if (GeneratorUtils.returnTypeIsMetadata(returnType)) {
                final PsiClass metadataType = GeneratorUtils.getMetadataType((PsiClass) returnType.resolve());
                importList.add(elementFactory.createImportStatement(metadataType));

                type = metadataType.getName();
            } else {

                if (returnType.getReferenceParameterList().getTypeElement() != null) {
                    importList.add(elementFactory.createImportStatement((PsiClass) returnType.getReferenceParameterList().getTypeElement().getCodeReferenceElement().resolve()));
                }

                type = returnType.getText();
            }
        }

        final PsiMethod executeMethod = elementFactory
                .createMethodFromText(
                        (returnType == null ? "void " : type) + " execute();",
                        null
                );

        executeMethod
                .getThrowsList()
                .add(elementFactory.createReferenceElementByFQClassName("java.sql.SQLException", GlobalSearchScope.allScope(metaSqlFile.getProject())));

        return executeMethod;
    }

    public static PsiMethod createExecutionMethodWithConnectionParameter(PsiElementFactory elementFactory, JavaPsiFacade javaPsiFacade, PsiImportList importList, MetaSqlFile metaSqlFile) {
        final MetaSqlCodeReferenceElement returnType = metaSqlFile.getReturnType();
        String type = null;

        if (returnType != null) {
            if (GeneratorUtils.returnTypeIsMetadata(returnType)) {
                final PsiClass metadataType = GeneratorUtils.getMetadataType((PsiClass) returnType.resolve());
                importList.add(elementFactory.createImportStatement(metadataType));

                type = metadataType.getName();
            } else {
                type = returnType.getText();
            }
        }


        importList.add(elementFactory.createImportStatement(javaPsiFacade.findClass("java.sql.Connection", GlobalSearchScope.allScope(metaSqlFile.getProject()))));

        final PsiMethod executeMethod = elementFactory.createMethodFromText((returnType == null ? "void " : type) + " execute(Connection connection);", null);
        executeMethod
                .getThrowsList()
                .add(elementFactory.createReferenceElementByFQClassName("java.sql.SQLException", GlobalSearchScope.allScope(metaSqlFile.getProject())));

        return executeMethod;
    }
}
