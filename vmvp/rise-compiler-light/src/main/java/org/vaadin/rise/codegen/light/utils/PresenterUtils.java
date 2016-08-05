package org.vaadin.rise.codegen.light.utils;

import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import org.vaadin.rise.core.annotation.NoGateKeeper;
import org.vaadin.rise.core.annotation.Presenter;

import javax.lang.model.type.MirroredTypeException;

/**
 * Created by oem on 8/5/16.
 */
public class PresenterUtils {
	public static Symbol.TypeSymbol getGateKeeper(Presenter presenter) {
		try {
			presenter.useGateKeeper();
		} catch (MirroredTypeException e) {
			if (NoGateKeeper.class.getName().equals(e.getTypeMirror().toString()))  {
				return null;
			}
			return ((Type.ClassType)e.getTypeMirror()).asElement();
		}
		return null;
	}
}
