package ru.bellski.metasql.lang.generator.builder;

import com.intellij.openapi.util.text.StringUtil;
import ru.bellski.metasql.lang.MetaSqlFile;
import ru.bellski.metasql.lang.psi.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by oem on 6/16/16.
 */
public class MetaQueryBuilder {
    private String name;
    private String query;
    private List<StepBuilder> stepBuilders;
    private String firstStep;
    private ExecutorBuilder executorBuilder;
    private int paramsCount;
    private List<String> importPackages;
    private String returnType;
    private MetaSqlReturnStatement returnTypeStatement;
    private String returnTemplate;

    public MetaQueryBuilder(String name, String query, MetaSqlRoot metaSqlRoot) {
        this.name = name.concat("Query");
        this.query = query;
        this.paramsCount = metaSqlRoot.getParametersCount();

        importPackages = metaSqlRoot.getImportNames();

        if (metaSqlRoot.getReturnStatement() == null) {
            this.returnType = "void";
        } else {
            this.returnType = metaSqlRoot.getReturnStatement().getTypeReference().getText();
        }

        executorBuilder = new ExecutorBuilder(this.name.concat("Executor"), metaSqlRoot.getReturnStatement());

        stepBuilders = new ArrayList<>();

        final MetaSqlParameterDefinition[] parameterDefinitions = metaSqlRoot.getParameters();

        for (int i = 0; i < parameterDefinitions.length; i++) {
            final StepBuilder stepBuilder = new StepBuilder(parameterDefinitions[i], i);

            if (parameterDefinitions.length > (i+1)) {
                final String nextStep = parameterDefinitions[i + 1].getIdentifier().getText();
                stepBuilder.setSetterReturnType(StringUtil.capitalize(nextStep));
            } else {
                stepBuilder.setSetterReturnType(executorBuilder.getName());
            }

            stepBuilders.add(stepBuilder);
        }


        if (stepBuilders.isEmpty()) {
            firstStep = executorBuilder.getName();
        } else {
            firstStep = stepBuilders.get(0).getName();
        }


    }

    @Override
    public String toString() {
        return  "import javax.sql.DataSource; \n"
                + "import java.sql.PreparedStatement;\n"
                + "import java.sql.ResultSet;\n"
                + "import java.sql.SQLException;\n"
                + "import java.util.ArrayList;\n"
                + resolveImport() + ";\n"
                +"import java.sql.Connection; \n\n"
                + "public class " + name + " implements " + joinSteps() + " { \n"
                + "    private DataSource ds; \n"
                + "    private final String query = \"" + query + "\"; \n"
                + "    private final Object[] params = new Object[" + paramsCount + "]; \n\n"
                + "    private " + name + "(){ \n"
                + "    } \n\n"
                + "    private " + name + "(DataSource ds) { \n"
                + "        this.ds = ds; \n"
                + "    } \n\n"
                + "    public static " + firstStep + " create(DataSource ds) { \n"
                + "        return new " + name + "(ds); \n"
                + "    } \n\n"
                + "    public static " + firstStep +  " create() { \n"
                + "        return new " + name + "(); \n"
                + "    } \n\n"
                + implementSetters() + "\n\n"
                + executorBuilder.getOverrideExecutions() + "\n\n"
                + "}"
                ;
    }

    private String resolveImport() {
        return importPackages
                .stream()
                .map(packageName -> "import " + packageName).collect(Collectors.joining("; \n"));
    }

    private String joinSteps() {
        String steps = stepBuilders
                .stream()
                .map(StepBuilder::getName)
                .collect(Collectors.joining(", "));

        if (steps.isEmpty()) {
            steps = executorBuilder.getName();
        } else {
            steps = steps.concat(", ").concat(executorBuilder.getName());
        }

        return steps;
    }

    private String implementSetters() {
        return stepBuilders
                .stream()
                .map(StepBuilder::getOverrideSetter)
                .collect(Collectors.joining("\n\n"));
    }

    public List<StepBuilder> getStepBuilders() {
        return stepBuilders;
    }

    public String getName() {
        return name;
    }

    public ExecutorBuilder getExecutorBuilder() {
        return executorBuilder;
    }
}
