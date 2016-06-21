package ru.bellski.metasql.lang.generator;

import com.intellij.openapi.util.text.StringUtil;
import ru.bellski.metasql.lang.psi.MetaSqlParameterDefinition;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by Aleksandr on 13.06.2016.
 */
public class MetaQueryGenerator {
    private static String metaClassTemplate;
    private static String implementedStepTemplate;

    static {
        try {
            metaClassTemplate = readTemplate("/templates/MetaQueryClass.template");
            implementedStepTemplate = readTemplate("/templates/ImplementedStep.template");
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generate(String className, String query, MetaSqlParameterDefinition[] parameterDefinitions, String returnType)  {

        final MetaQuery metaQuery = new MetaQuery(className, generateSteps(parameterDefinitions, className, returnType));
        metaQuery.setQuery(query);

        String classTemplate = writeClassName(metaQuery.getName(), metaClassTemplate);
        classTemplate = writeSteps(metaQuery.getSteps(), classTemplate);
        classTemplate = writeQueryString(metaQuery.getQuery(), classTemplate);
        classTemplate = writeParamsCount(parameterDefinitions.length, classTemplate);
        classTemplate = writeFirstStep(metaQuery.getFirstStep().getName(), classTemplate);
        classTemplate = writeImplementSteps(metaQuery.getSteps(), classTemplate);

        return classTemplate;
    }

    private static List<Step> generateSteps(MetaSqlParameterDefinition[] parameterDefinitions, String className, String returnType) {
        final List<Step> steps = new ArrayList<>();

        final ExecutorStep executorStep = new ExecutorStep(className, returnType);

        for (int i = 0; i < parameterDefinitions.length; i++) {
            final MetaSqlParameterDefinition parameterDefinition = parameterDefinitions[i];
            final String id = parameterDefinition.getIdentifier().getText();
            final String type = parameterDefinition.getLiteralType().getText();

            final String nextStep = parameterDefinitions.length == (i +1) ?
                            executorStep.getName() : "set".concat(StringUtil.capitalize(parameterDefinitions[i+1].getIdentifier().getText()));

            final List<StepMethod> setStepMethods =
                    Collections.singletonList(
                            new StepMethod(
                                    "set".concat(StringUtil.capitalize(id)),
                                    nextStep,
                                    new StepMethodParameter(type, "value")
                            )
                    );

            steps.add(new SetParameterStep(setStepMethods));
        }

        steps.add(executorStep);

        return steps;
    }

    private static String readTemplate(String templateName) throws IOException, URISyntaxException {
        return new String(Files.readAllBytes(Paths.get(MetaQueryGenerator.class.getResource(templateName).toURI())));
    }

    private static String writeClassName(String className, String metaClassTemplate) {
        return metaClassTemplate.replaceAll("\\{metaQueryClassName\\}", className);
    }

    private static String writeSteps(List<Step> steps, String classTemplate) {
        return classTemplate
                .replaceAll(
                        "\\{steps\\}",
                        steps
                                .stream()
                                .map(step -> StringUtil.capitalize(step.getName()))
                                .collect(Collectors.joining(", "))
                );
    }

    private static String writeQueryString(String query, String classTemplate) {
        return classTemplate.replaceAll("\\{queryString\\}", query);
    }

    private static String writeParamsCount(int count, String classTemplate) {
        return classTemplate.replaceAll("\\{paramsCount\\}", String.valueOf(count));
    }

    private static String writeFirstStep(String firstStep, String classTemplate) {
        return classTemplate.replaceAll("\\{firstStep\\}", StringUtil.capitalize(firstStep));
    }

    private static String writeImplementSteps(List<Step> steps, String classTemplate) {
        final StringJoiner stringJoiner = new StringJoiner("\n\n");

        for (int i = 0; i < steps.size(); i++) {
            final Step setParameterStep = steps.get(i);

            int finalI = i;
            setParameterStep.getStepMethods().forEach(stepMethod -> {
                final StepMethodParameter parameter = stepMethod.getStepMethodParameter();

                if (parameter != null) {
                    String setterTemplate = implementedStepTemplate.replace("{methodName}", StringUtil.capitalize(stepMethod.getName()));
                    setterTemplate = setterTemplate.replace("{paramIndex}", String.valueOf(finalI));
                    setterTemplate = setterTemplate.replace("{paramType}", parameter.getType());
                    setterTemplate = setterTemplate.replace("{paramName}", parameter.getName());
                    setterTemplate = setterTemplate.replace("{nextStep}", stepMethod.getReturnType());
                    stringJoiner.add(setterTemplate);
                }
            });
        }

        return classTemplate.replace("{implementSteps}", stringJoiner.toString());
    }
}
