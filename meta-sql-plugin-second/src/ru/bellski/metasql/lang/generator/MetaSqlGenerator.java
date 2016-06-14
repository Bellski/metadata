package ru.bellski.metasql.lang.generator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Aleksandr on 13.06.2016.
 */
public class MetaSqlGenerator {
    private static String metaClassTemplate;

    static {
        readTemplate("/MetaSqlClass.template");
    }

    public static String generate()  {

    }

    private static String readTemplate(String templateName) throws IOException, URISyntaxException {
        return new String(Files.readAllBytes(Paths.get(MetaSqlGenerator.class.getResource(templateName).toURI())));
    }

    public static void writeClassName(String className, String metaClassTemplate) {
        metaClassTemplate = metaClassTemplate.replaceAll("\\{className\\}", className);
    }
}
