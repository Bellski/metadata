package org.vaadin.rise.codegen.generator;


import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.vaadin.rise.codegen.model.ModuleModel;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.List;

/**
 * Created by Aleksandr on 16.07.2016.
 */
public class ModuleGenerator extends Generator<ModuleModel> {

    public ModuleGenerator(Configuration freemarker, Filer filer) {
        super(freemarker, filer);
    }

    @Override
    public List<JavaFileObject> generate(List<ModuleModel> models) throws IOException, TemplateException {
        return generate("Module.ftl", models);
    }
}
