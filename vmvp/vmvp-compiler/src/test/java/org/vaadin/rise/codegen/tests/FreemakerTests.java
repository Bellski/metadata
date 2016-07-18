package org.vaadin.rise.codegen.tests;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.vaadin.rise.codegen.freemaker.FQN;
import org.vaadin.rise.codegen.freemaker.ModuleModel;
import org.vaadin.rise.codegen.freemaker.ProvidesSlotModel;
import org.vaadin.rise.codegen.freemaker.ProvidesVPModel;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by Aleksandr on 16.07.2016.
 */
@RunWith(JUnit4.class)
public class FreemakerTests {
    private static final String BASE_PACKAGE = "rise.freemaker.test";

    @Test
    public void testTemplate() throws IOException, TemplateException {
//        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
//        cfg.setClassForTemplateLoading(this.getClass(), "/template");
//        cfg.setDefaultEncoding("UTF-8");
//        cfg.setLogTemplateExceptions(false);
//
//        ModuleModel moduleModel = new ModuleModel(new FQN("RiseModule", BASE_PACKAGE), new FQN("TestModule", BASE_PACKAGE));
//        moduleModel.setPackageName(BASE_PACKAGE);
//        moduleModel.include(new FQN("ChildModule", BASE_PACKAGE));
//
//        moduleModel.setProvidesView(new ProvidesVPModel(new FQN("Test.View", BASE_PACKAGE), new FQN("TestView", BASE_PACKAGE)));
//        moduleModel.setProvidesPresenter(new ProvidesVPModel(new FQN("Test.Presenter", BASE_PACKAGE), new FQN("TestPresenter", BASE_PACKAGE)));
//
//        moduleModel.addSlot(new ProvidesSlotModel(new FQN("Test.Slo1", BASE_PACKAGE), new FQN("TestPresenterProxy.Slot1", BASE_PACKAGE)));
//
//
//        Writer out = new OutputStreamWriter(System.out);
//        cfg.getTemplate("Module.ftl").process(moduleModel, out);
    }
}
