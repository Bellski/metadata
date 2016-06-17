package ru.bellski.metasql.lang.generator.builder;

import com.intellij.openapi.util.text.StringUtil;
import ru.bellski.metasql.lang.psi.MetaSqlCollection;
import ru.bellski.metasql.lang.psi.MetaSqlParameterDefinition;
import ru.bellski.metasql.lang.psi.MetaSqlReturnType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
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
    private String returnType;
    private MetaSqlReturnType returnTypeElement;

    public MetaQueryBuilder(String name, MetaSqlParameterDefinition[] parameterDefinitions,String query, MetaSqlReturnType returnTypeElement) {
        this.name = name.concat("Query");
        this.query = query;
        this.paramsCount = parameterDefinitions.length;
        this.returnTypeElement = returnTypeElement;

        if (returnType == null) {
            this.returnType = "void";
        } else {
            this.returnType = returnTypeElement.getText();
        }

        executorBuilder = new ExecutorBuilder(this.name.concat("Executor"), this.returnType);

        stepBuilders = new ArrayList<>();

        for (int i = 0; i < parameterDefinitions.length; i++) {
            final MetaSqlParameterDefinition parameterDefinition = parameterDefinitions[i];
            final String paramName = parameterDefinition.getIdentifier().getText();
            final String paramType = parameterDefinition.getPrimitives().getText();

            final StepBuilder stepBuilder = new StepBuilder(StringUtil.capitalize(paramName), i);
            stepBuilder.setSetterName("set".concat(StringUtil.capitalize(paramName)));
            stepBuilder.setSetterParamType(paramType);
            stepBuilder.setSetterParamName(paramName);


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
                + resolveImport() + "\n"
                +"import java.sql.Connection; \n\n"
                + "public class " + name + " implements " + joinSteps() + " { \n"
                + "    private DataSource ds; \n"
                + "    private final String query = " + query + "\n"
                + "    private final Object[] params = new Object[" + paramsCount + "]; \n\n"
                + "    private Test() { \n"
                + "    } \n\n"
                + "    private Test(DataSource ds) { \n"
                + "        this.ds = ds; \n"
                + "    } \n\n"
                + "    public static " + firstStep + " create(DataSource ds) { \n"
                + "        return new " + name + "(ds); \n"
                + "    } \n\n"
                + "    public static " + firstStep +  " create() { \n"
                + "        return new Test(); \n"
                + "    } \n\n"
                + implementSetters() + "\n\n"
                + executorBuilder.getOverrideExecutions() + "\n\n"
                + "}"
                ;
    }

    private String resolveImport() {
        final StringJoiner stringJoiner = new StringJoiner("\n");

        if (returnTypeElement != null) {
            final MetaSqlCollection collection = returnTypeElement.getCollection();

            if (collection != null) {
                stringJoiner.add("import ".concat(collection.getReturnList().getPackageName()).concat(";"));
            }
        }

        return stringJoiner.toString();
    }

    private String joinSteps() {
        String steps = stepBuilders
                .stream()
                .map(StepBuilder::getName)
                .collect(Collectors.joining(", "));

        if (steps == null) {
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
}
