package ru.bellski.metasql.lang.generator;

import com.intellij.codeInsight.template.TemplateBuilderFactory;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.spellchecker.util.Strings;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Aleksandr on 13.06.2016.
 */
public class MetaQueryGenerator {
    private static String metaClassTemplate;

    static {
        try {
            metaClassTemplate = readTemplate("/templates/MetaQueryClass.template");
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generate(String className, String query, List<String> paramNames)  {
        final MetaQuery metaQuery = new MetaQuery(className, paramNames);
        metaQuery.setQuery(query);


        String classTemplate = writeClassName(metaQuery.getName(), metaClassTemplate);
        classTemplate = writeSteps(metaQuery.getSteps(), classTemplate);
        classTemplate = writeQueryString(metaQuery.getQuery(), classTemplate);
        classTemplate = writeParamsCount(paramNames.size(), classTemplate);



        return classTemplate;
    }

    private static String readTemplate(String templateName) throws IOException, URISyntaxException {
        return new String(Files.readAllBytes(Paths.get(MetaQueryGenerator.class.getResource(templateName).toURI())));
    }

    private static String writeClassName(String className, String metaClassTemplate) {
        return metaClassTemplate.replaceAll("\\{metaQueryClassName\\}", className);
    }

    private static String writeSteps(List<SetParameterStep> paramNames, String classTemplate) {
        return classTemplate
                .replaceAll(
                        "\\{steps\\}",
                        paramNames
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
}
