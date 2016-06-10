package ru.bellski.metasql.generate;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiManager;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by Aleksandr on 30.05.2016.
 */
public class QueryClass {
    private final JavaClassSource queryClassSource;
    private final String name;
    private final String packageName;
    private final String query;
    private List<ParameterInterface> parameterInterfaces;
    private ExecutionStepInterface executionStepInterface;


    private QueryClass(String name, String packageName, String query, JavaClassSource javaClassSource) {
        this.queryClassSource = javaClassSource;
        this.name = name;
        this.packageName = packageName;
        this.query = query;

        queryClassSource.setPackage(packageName);

        initImports();
        initFields();
        initConstructors();
    }

    public static QueryClass create(String name, String packageName, String query) {
        return new QueryClass(name, packageName, query, Roaster.create(JavaClassSource.class));
    }


    private void initImports() {
        queryClassSource.setPackage(packageName);
        queryClassSource.setName(name);
        queryClassSource.addImport(Connection.class);
        queryClassSource.addImport(SQLException.class);
        queryClassSource.addImport(PreparedStatement.class);
        queryClassSource.addImport(ResultSet.class);
    }

    private void initFields() {
        queryClassSource
                .addField()
                .setPrivate()
                .setType(DataSource.class)
                .setName("dataSource");

        queryClassSource
                .addField()
                .setPrivate()
                .setType(String.class)
                .setName("query")
                .setStringInitializer(query);
    }

    private void initConstructors() {
        queryClassSource
                .addMethod()
                .setPrivate()
                .setName(name)
                .setConstructor(true)
                .setBody("");

        queryClassSource
                .addMethod()
                .setPrivate()
                .setName(name)
                .setConstructor(true)
                .setBody("this.dataSource = dataSource;")
                .addParameter(DataSource.class, "dataSource");
    }

    private void initCreateMethods(ParameterInterface parameterInterface) {
        queryClassSource
                .addMethod()
                .setPublic()
                .setStatic(true)
                .setName("create")
                .setReturnType(parameterInterface.getType())
                .setBody(
                        "return new " + name + "();"
                );

        queryClassSource
                .addMethod()
                .setPublic()
                .setStatic(true)
                .setName("create")
                .setReturnType(parameterInterface.getType())
                .setBody(
                        "return new " + name  + "(dataSource);"
                )
                .addParameter(DataSource.class, "dataSource");
    }

    public QueryClass addParamInterfaces(List<ParameterInterface> parameterInterfaces) {
        this.parameterInterfaces = parameterInterfaces;
        buildParameterField(parameterInterfaces.size());
        initCreateMethods(parameterInterfaces.get(0));
        implementsParameterInterfaces(parameterInterfaces);
        return this;
    }

    private void implementsParameterInterfaces(List<ParameterInterface> parameterInterfaces) {
        for (int i = 0; i < parameterInterfaces.size(); i++) {
            final ParameterInterface parameterInterface = parameterInterfaces.get(i);

            queryClassSource.implementInterface(parameterInterface.getType());

            queryClassSource.getMethod(
                    parameterInterface
                            .getParamSetter()
                            .getName(),
                    parameterInterface
                            .getParamSetter()
                            .getParameter()
            )
                    .setBody(
                            "parameters[" + i + "] = value; \n"
                                    +"return this;"
                    );
        }
    }

    private void buildParameterField(int size) {
        queryClassSource
                .addField()
                .setType(Object[].class)
                .setName("parameters")
                .setLiteralInitializer(" new Object[" + size + "];");

    }

    public QueryClass addExecutionStepInterface(ExecutionStepInterface executionStepInterface) {
        this.executionStepInterface = executionStepInterface;
        queryClassSource.implementInterface(executionStepInterface.getType());

        final StringBuilder setParamsBuilder = new StringBuilder();

        for (int i = 0 ; i < parameterInterfaces.size() ; i++) {
            setParamsBuilder
                    .append("pstm.setObject(")
                    .append(i+1)
                    .append(",")
                    .append("parameters[")
                    .append(i)
                    .append("]);")
                    .append("\n");
        }

        queryClassSource
                .getMethod("execute", Connection.class)
                .setBody(
                        "final PreparedStatement pstm = connection.prepareStatement(query); \n"
                        +setParamsBuilder.toString() + "\n"
                        +"ResultSet resultSet = pstm.executeQuery(); \n"

                );
        return this;
    }

    public void save(VirtualFile directoryFile, Project project) {
        final PsiFileFactory psiFileFactory = PsiFileFactory.getInstance(project);
        final PsiManager psiManager = PsiManager.getInstance(project);

        VirtualFile metaSqlPackage =
                directoryFile.findFileByRelativePath("target/generated-sources/meta/" + packageName.replaceAll("\\.", "/"));

        if (metaSqlPackage != null) {
            ApplicationManager.getApplication().runWriteAction(new Runnable() {
                @Override
                public void run() {
                    VirtualFile queryDir = metaSqlPackage.findChild("query");
                    if (queryDir == null) {
                        try {
                            queryDir = metaSqlPackage.createChildDirectory(this, "query");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    final VirtualFile queryClassFile = queryDir.findChild(name + ".java");

                    if (queryClassFile == null) {
                        psiManager
                                .findDirectory(queryDir)
                                .add(
                                        psiFileFactory.createFileFromText( name + ".java", JavaFileType.INSTANCE, normalizeClassSource(queryClassSource.toString()))
                                );
                    }

                    for (ParameterInterface parameterInterface : parameterInterfaces) {
                        final VirtualFile parameterInterfaceFile = queryDir.findChild(parameterInterface.getJavaFileName());

                        if (parameterInterfaceFile == null) {
                            psiManager
                                    .findDirectory(queryDir)
                                    .add(
                                            psiFileFactory.createFileFromText(parameterInterface.getJavaFileName(), JavaFileType.INSTANCE, normalizeClassSource(parameterInterface.toString()))
                                    );
                        }
                    }

                    VirtualFile executionStepInterfaceFile = queryDir.findChild(executionStepInterface.getFileName());

                    if (executionStepInterfaceFile == null) {
                        psiManager
                                .findDirectory(queryDir)
                                .add(
                                        psiFileFactory.createFileFromText(executionStepInterface.getFileName(), JavaFileType.INSTANCE, normalizeClassSource(executionStepInterface.toString()))
                                );
                    }
                }
            });
        }
    }

    private String normalizeClassSource(String source) {
        return source.replaceAll("\\r", "");
    }
}
