package org.vaadin.rise.codegen.step;

import org.vaadin.rise.codegen.helpers.JavaFileObjects;
import org.vaadin.rise.codegen.model.JFOModel;

import javax.tools.JavaFileObject;
import java.util.List;

/**
 * Created by oem on 7/19/16.
 */
public interface DaggerJFOSGenerateCallBack {
	void onJFOSGenerate(List<JavaFileObject> jfos);
}
