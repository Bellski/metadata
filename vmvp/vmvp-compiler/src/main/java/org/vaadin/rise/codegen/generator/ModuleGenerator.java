package org.vaadin.rise.codegen.generator;


import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.testing.compile.JavaFileObjects;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.vaadin.rise.codegen.freemaker.ModuleModel;

import javax.annotation.processing.Filer;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Set;

/**
 * Created by Aleksandr on 16.07.2016.
 */
public class ModuleGenerator {

    private final Configuration freemakerConf;
    private final Filer filer;

    public ModuleGenerator(Configuration freemakerConf, Filer filer) {
        this.freemakerConf = freemakerConf;
        this.filer = filer;
    }

    public ImmutableCollection<JavaFileObject> generate(Set<ModuleModel> moduleModels, Elements elements) throws IOException, TemplateException {
        final ImmutableCollection.Builder<JavaFileObject> jfosBuilder = ImmutableSet.builder();



        for (ModuleModel moduleModel : moduleModels) {

            final JavaFileObject moduleJFO = filer.createSourceFile(moduleModel.getFQN());

            try (Writer out = new StringWriter(); Writer moduleOut = moduleJFO.openWriter()) {

                freemakerConf.getTemplate("Module.ftl").process(moduleModel, out);
                freemakerConf.getTemplate("Module.ftl").process(moduleModel, moduleOut);

                jfosBuilder.add(JavaFileObjects.forSourceLines(moduleModel.getFQN(), out.toString()));
            }
        }


        return jfosBuilder.build();
    }
}
