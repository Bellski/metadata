package ru.bellski.metasql.generate;

import com.intellij.codeInsight.actions.AbstractLayoutCodeProcessor;
import com.intellij.codeInsight.actions.ReformatCodeProcessor;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.psi.tree.IElementType;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.*;
import org.jetbrains.idea.maven.project.MavenProject;
import ru.bellski.metasql.lang.psi.MetaSqlBody;
import ru.bellski.metasql.lang.psi.MetaSqlParameter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oem on 5/26/16.
 */
public class SqlQueryGenerator {

    public static void generate(String className, String packageName, String sqlQuery, MetaSqlBody metaSqlBody, MavenProject mProject, Project project) {
        final PsiFileFactory psiFileFactory = PsiFileFactory.getInstance(project);

        final PsiDirectory projectDir = PsiManager
                .getInstance(project)
                .findDirectory(mProject.getDirectoryFile())
                .findSubdirectory("target")
                .findSubdirectory("generated-sources")
                .findSubdirectory("meta");

        final JavaClassSource queryClass = Roaster.create(JavaClassSource.class);
        queryClass.setPackage(packageName);
        queryClass.setName(className);
        queryClass.addImport(Connection.class);
        queryClass.addImport(SQLException.class);
        queryClass.addImport(PreparedStatement.class);
        queryClass.addImport(ResultSet.class);

        queryClass
                .addMethod()
                .setPrivate()
                .setName(className)
                .setConstructor(true)
                .setBody("");

        queryClass
                .addMethod()
                .setPrivate()
                .setName(className)
                .setConstructor(true)
                .setBody("this.dataSource = dataSource;")
                .addParameter(DataSource.class, "dataSource");

        queryClass
                .addField()
                .setPrivate()
                .setType(DataSource.class)
                .setName("dataSource");

        final JavaInterfaceSource executionStep = Roaster.create(JavaInterfaceSource.class);
        executionStep.setPackage(packageName);
        executionStep.setName(className + "Execution");
        executionStep
                .addMethod()
                .setPublic()
                .setReturnType("void")
                .setName("execute")
                .addThrows(SQLException.class);

        executionStep
                .addMethod()
                .setPublic()
                .setReturnType("void")
                .setName("execute")
                .addThrows(SQLException.class)
                .addParameter(Connection.class, "connection");


        List<JavaInterfaceSource> paramInterfaces = buildParamInterfaces(metaSqlBody.getParameterArray().getParameterList().getParameterList(), packageName, executionStep);

        queryClass
                .addMethod()
                .setPublic()
                .setStatic(true)
                .setName("create")
                .setReturnType(paramInterfaces.get(0))
                .setBody(
                        "return new " + className + "();"
                );

        queryClass
                .addMethod()
                .setPublic()
                .setStatic(true)
                .setName("create")
                .setReturnType(paramInterfaces.get(0))
                .setBody(
                        "return new " + className + "(dataSource);"
                )
                .addParameter(DataSource.class, "dataSource");

        StringBuilder setParamsBuilder = new StringBuilder();

        for (int i = 0; i < paramInterfaces.size(); i++) {
            final JavaInterfaceSource paramInterface = paramInterfaces.get(i);

            queryClass.implementInterface(paramInterface);

            final MethodSource<JavaInterfaceSource> setter = paramInterface.getMethods().get(0);

            queryClass
                    .getMethod(setter.getName(), setter.getParameters().get(0).getType().getQualifiedName())
                    .setBody(
                            "parameters[" + i + "] = value; \n"
                                    +"return this;"
                    );

            setParamsBuilder
                    .append("pstm.setObject(")
                    .append(i+1)
                    .append(",")
                    .append("parameters[")
                    .append(i)
                    .append("]);")
                    .append("\n");
        }

        String unmarshall = "";

        if ("Single".equals(metaSqlBody.getReturnRuleClause().getReturnRuleInit().getReturnRuleValue().getText())) {
            PsiClass metadata = (PsiClass) metaSqlBody.getMetadataClause().getMetadataInit().getMetadataReference().resolve();



            executionStep
                    .getMethod("execute")
                    .setReturnType(metadata.getNameIdentifier().getText());

            executionStep
                    .getMethod("execute", Connection.class);

            queryClass.addImport(metadata.getQualifiedName());

            unmarshall = "return " + metadata.getNameIdentifier().getText() + ".unmarshal(resultSet);";
        }

        queryClass.implementInterface(executionStep);
        queryClass
                .getMethod("execute")
                .setBody(
                        "try(Connection connection = dataSource.getConnection()) {execute(connection);}"
                );




        queryClass
                .getMethod("execute", Connection.class)
                .setBody(
                        "final PreparedStatement pstm = connection.prepareStatement(query); \n"
                        +setParamsBuilder.toString() + "\n"
                        +"ResultSet resultSet = pstm.executeQuery(); \n"
                        +unmarshall
                );

        queryClass
                .addField()
                .setType(Object[].class)
                .setName("parameters")
                .setLiteralInitializer(" new Object[" + paramInterfaces.size() + "];");

        queryClass
                .addField()
                .setType(String.class)
                .setName("query")
                .setStringInitializer(sqlQuery);

        ApplicationManager.getApplication().runWriteAction(() -> {
            final PsiDirectory metaDir = projectDir.createSubdirectory("queries");
            final PsiFile executionStepFile = (PsiFile) metaDir.add(psiFileFactory.createFileFromText(executionStep.getName() + ".java", JavaFileType.INSTANCE, normalizeClassSource(executionStep.toUnformattedString())));
            final PsiFile queryFile = (PsiFile) metaDir.add(psiFileFactory.createFileFromText(queryClass.getName() + ".java", JavaFileType.INSTANCE, normalizeClassSource(queryClass.toUnformattedString())));

            AbstractLayoutCodeProcessor reformat = new ReformatCodeProcessor(project, queryFile, null, false);
            reformat.run();

            AbstractLayoutCodeProcessor reformat1 = new ReformatCodeProcessor(project, executionStepFile, null, false);
            reformat1.run();

            for (JavaInterfaceSource paramInterface : paramInterfaces) {
                final PsiFile paramInterfaceFile = (PsiFile) metaDir.add(psiFileFactory.createFileFromText(paramInterface.getName() + ".java", JavaFileType.INSTANCE, normalizeClassSource(paramInterface.toUnformattedString())));

                AbstractLayoutCodeProcessor reformat2 = new ReformatCodeProcessor(project, paramInterfaceFile, null, false);
                reformat2.run();
            }
        });
    }

    private static List<JavaInterfaceSource> buildParamInterfaces(List<MetaSqlParameter> parameterList, String packageName, JavaInterfaceSource executionStep) {
        final List<JavaInterfaceSource> paramInterfaces = new ArrayList<>();

        for (int i = 0; i < parameterList.size(); i++) {
            MetaSqlParameter metaSqlParameter = parameterList.get(i);

            final JavaInterfaceSource paramInterface = Roaster.create(JavaInterfaceSource.class);
            paramInterface.setPackage(packageName);
            paramInterface.setName(buildParamInterfaceName(metaSqlParameter));

            if (i != parameterList.size() - 1) {
                paramInterface
                        .addMethod()
                        .setPublic()
                        .setReturnType(buildParamInterfaceName(parameterList.get(i + 1)))
                        .setName(buildParamSetter(metaSqlParameter))
                        .addParameter(metaSqlParameter.getParameterType().getText(), "value");
            } else {
                paramInterface
                        .addMethod()
                        .setPublic()
                        .setReturnType(executionStep)
                        .setName(buildParamSetter(metaSqlParameter))
                        .addParameter(metaSqlParameter.getParameterType().getText(), "value");
            }

            paramInterfaces.add(paramInterface);
        }

        return paramInterfaces;
    }

    private static String buildParamInterfaceName(MetaSqlParameter metaSqlParameter) {
        return
                "Set"
                        + StringUtil.wordsToBeginFromUpperCase(metaSqlParameter.getParameterKeyword().getText())
                        + "Step";
    }

    private static String buildParamSetter(MetaSqlParameter metaSqlParameter) {
        return "set"
               + StringUtil.wordsToBeginFromUpperCase(metaSqlParameter.getParameterKeyword().getText());
    }

    private static String buildExecutionMethod(MetaSqlParameter metaSqlParameter, JavaInterfaceSource executionInterface) {
        return
                executionInterface.getName()
                        + " set"
                        + StringUtil.wordsToBeginFromUpperCase(metaSqlParameter.getParameterKeyword().getText())
                        + "("
                        + metaSqlParameter.getParameterType().getText()
                        + " value" + ");";
    }

    private static String normalizeClassSource(String source) {
        return source.replaceAll("\\r", "");
    }
}
