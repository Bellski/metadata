package org.vaadin.rise.codegen.ligt.tests;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Aleksandr on 07.08.2016.
 */
public class TestFreemarker {
    public static void main(String[] args) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
        cfg.setClassForTemplateLoading(TestFreemarker.class, "/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLogTemplateExceptions(false);

        Template template = cfg.getTemplate("Test.ftl");

        try(Writer writer = new StringWriter()) {
            template.process(null, writer);

            System.out.println(writer.toString());
        }
    }
}
