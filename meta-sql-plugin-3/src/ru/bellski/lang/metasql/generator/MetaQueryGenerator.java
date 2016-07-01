package ru.bellski.lang.metasql.generator;

import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import ru.bellski.lang.metasql.MetaSqlFile;
import ru.bellski.lang.metasql.psi.MetaSqlCodeReferenceElement;
import ru.bellski.lang.metasql.psi.MetaSqlParameterVariable;

import java.util.List;

/**
 * Created by Aleksandr on 26.06.2016.
 */
public class MetaQueryGenerator {

    public static PsiClass generate(MetaSqlFile metaSqlFile, PsiClass stepInterfaces[], PsiClass executorInterface) {
        final Project project = metaSqlFile.getProject();
        final PsiElementFactory elementFactory = PsiElementFactory.SERVICE.getInstance(project);
        final JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(project);

        final PsiClass queryClass = elementFactory.createClass(metaSqlFile.getQueryName());
        final PsiJavaFile javaFile = (PsiJavaFile) queryClass.getContainingFile();

        javaFile
                .getImportList()
                .add(importSqlDataSourceClass(elementFactory, javaPsiFacade, project));

        javaFile
                .getImportList()
                .add(importPreparedStatementClass(elementFactory, javaPsiFacade, project));

        javaFile
                .getImportList()
                .add(importResultSetClass(elementFactory, javaPsiFacade, project));

        queryClass
                .getImplementsList()
                .add(createImplementsReference(elementFactory, executorInterface));

        for (PsiClass stepInterface : stepInterfaces) {
            queryClass
                    .getImplementsList()
                    .add(createImplementsReference(elementFactory, stepInterface));
        }

        queryClass.add(createDataSourceField(elementFactory));
        queryClass.add(createQueryField(elementFactory, metaSqlFile.getQuery()));
        queryClass.add(createParamsField(elementFactory, metaSqlFile.getParamsCount()));
        queryClass.add(createEmptyConstructor(elementFactory));
        queryClass.add(createConstructorWithDataSourceParameter(elementFactory));


        queryClass.add(
                createCreateMethod(
                        elementFactory,
                        stepInterfaces.length == 0 ? executorInterface : stepInterfaces[0],
                        queryClass.getName()
                )
        );

        queryClass.add(
                createCreateMethodWithDataSourceParameter(
                        elementFactory,
                        stepInterfaces.length == 0 ? executorInterface : stepInterfaces[0],
                        queryClass.getName()
                )
        );

        implementsExecutorInterface(queryClass, elementFactory, executorInterface, metaSqlFile.getParameters(), metaSqlFile.getReturnType(), javaFile.getImportList());
        implementsSetterMethods(queryClass, elementFactory, stepInterfaces);

        return queryClass;
    }

    private static PsiField createDataSourceField(PsiElementFactory elementFactory) {
        return elementFactory.createFieldFromText("private DataSource ds;", null);
    }

    private static PsiImportStatement importSqlDataSourceClass(PsiElementFactory elementFactory, JavaPsiFacade javaPsiFacade, Project project) {
        return elementFactory.createImportStatement(
                javaPsiFacade.findClass("javax.sql.DataSource", GlobalSearchScope.allScope(project))
        );
    }

    private static PsiImportStatement importPreparedStatementClass(PsiElementFactory elementFactory, JavaPsiFacade javaPsiFacade, Project project) {
        return elementFactory.createImportStatement(
                javaPsiFacade.findClass("java.sql.PreparedStatement", GlobalSearchScope.allScope(project))
        );
    }

    private static PsiImportStatement importResultSetClass(PsiElementFactory elementFactory, JavaPsiFacade javaPsiFacade, Project project) {
        return elementFactory.createImportStatement(
                javaPsiFacade.findClass("java.sql.ResultSet", GlobalSearchScope.allScope(project))
        );
    }

    public static PsiJavaCodeReferenceElement createImplementsReference(PsiElementFactory elementFactory, PsiClass psiClass) {
        return elementFactory.createClassReferenceElement(psiClass);
    }

    private static PsiField createQueryField(PsiElementFactory elementFactory, String query) {
        return elementFactory.createFieldFromText("private String query = \"" + query + "\";", null);
    }

    private static PsiField createParamsField(PsiElementFactory elementFactory, int paramsCount) {
        return elementFactory.createFieldFromText("private Object[] params = new Object[" + paramsCount + "];", null);
    }

    private static PsiMethod createEmptyConstructor(PsiElementFactory elementFactory) {
        final PsiMethod constructor = elementFactory.createConstructor();
        constructor.getModifierList().setModifierProperty("public", false);
        constructor.getModifierList().setModifierProperty("private", true);

        return constructor;
    }

    private static PsiMethod createConstructorWithDataSourceParameter(PsiElementFactory elementFactory) {
        final PsiMethod constructor = elementFactory.createConstructor();
        constructor
                .getModifierList()
                .setModifierProperty("public", false);

        constructor
                .getModifierList()
                .setModifierProperty("private", true);

        constructor
                .getParameterList()
                .add(elementFactory.createParameter("ds", elementFactory.createTypeFromText("DataSource", null)));

        constructor
                .getBody()
                .replace(elementFactory.createCodeBlockFromText("{this.ds = ds;}", null));

        return constructor;
    }

    public static PsiMethod createCreateMethod(PsiElementFactory elementFactory, PsiClass firstStep, String className) {

        return elementFactory.createMethodFromText(
                "public static " + firstStep.getName() + " create() {"
                        + "return new " + className + "();"
                        + "}"
                , null
        );
    }

    public static PsiMethod createCreateMethodWithDataSourceParameter(PsiElementFactory elementFactory, PsiClass firstStep, String className) {
        return elementFactory.createMethodFromText(
                "public static " + firstStep.getName() + " create(DataSource ds) {"
                        + "return new " + className + "(ds);"
                        + "}"
                , null
        );
    }

    private static void implementsSetterMethods(PsiClass queryClass, PsiElementFactory elementFactory, PsiClass stepInterfaces[]) {

        for (int i = 0; i < stepInterfaces.length; i++) {
            final PsiMethod overrideMethod = (PsiMethod) stepInterfaces[i].getMethods()[0].copy();

            overrideMethod
                    .getModifierList()
                    .setModifierProperty("public", true);

            overrideMethod.getModifierList().addAnnotation("java.lang.Override");

            overrideMethod.add(elementFactory.createCodeBlockFromText(
                    "{"
                            + "params[" + i + "] = value;"
                            + "return this;"
                            + "}",
                    null
            ));

            queryClass.add(overrideMethod);
        }
    }

    private static void implementsExecutorInterface(PsiClass queryClass, PsiElementFactory elementFactory, PsiClass executorInterface, List<MetaSqlParameterVariable> parameters, MetaSqlCodeReferenceElement returnType, PsiImportList importList) {
        final PsiMethod[] overrideMethods = executorInterface.getMethods();

        final PsiMethod overrideExecuteMethod = createExecuteMethod(overrideMethods[0]);
        overrideExecuteMethod.add(
                elementFactory.createCodeBlockFromText(
                         "{"
                                 + "try (Connection connection = ds.getConnection()) {"
                                        + (overrideExecuteMethod.getReturnType().getPresentableText().equals("void") ? "execute(connection);" :  "return execute(connection);")
                                 + "}"
                        + "}",
                        null
                )
        );

        queryClass.add(overrideExecuteMethod);

        final PsiMethod overrideExecuteMethodWithConnectionParameter = createExecuteMethod(overrideMethods[1]);
        overrideExecuteMethodWithConnectionParameter.add(
                elementFactory.createCodeBlockFromText(
                        "{"
                                + GeneratorUtils.returnResult(returnType, importList, elementFactory)
                                + "final PreparedStatement stmp = connection.prepareStatement(query);"
                                + GeneratorUtils.prepareStatementSetParameters(parameters)
                                + "final ResultSet resultSet = stmp.executeQuery();"
                                + ResultSetRules.getGeneratedResultForType(overrideMethods[1].getReturnTypeElement(), returnType)
                                + ResultSetRules.generateReturnResult(overrideMethods[1].getReturnType())
                        + "}",
                        null
                )
        );

        queryClass.add(overrideExecuteMethodWithConnectionParameter);
    }

    private static PsiMethod createExecuteMethod(PsiMethod executeMethod) {
        final PsiMethod overrideMethod = (PsiMethod) executeMethod.copy();
        overrideMethod
                .getModifierList()
                .setModifierProperty("public", true);

        overrideMethod.getModifierList().addAnnotation("java.lang.Override");

        return overrideMethod;
    }

}
