package org.vaadin.rise.core.annotation;

import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;

/**
 * Created by oem on 7/19/16.
 */
public class RiseModuleHelper {
	public static TypeMirror presenterValue(RiseModule riseModule) {
		try {
			riseModule.presenter();
		} catch (MirroredTypeException e) {
			return e.getTypeMirror();
		}

		//Never happened;
		return null;
	}

	public static TypeMirror presenterApiValue(RiseModule riseModule) {
		try {
			riseModule.presenterApi();
		} catch (MirroredTypeException e) {
			return e.getTypeMirror();
		}

		//Never happened;
		return null;
	}

	public static TypeMirror viewValue(RiseModule riseModule) {
		try {
			riseModule.view();
		} catch (MirroredTypeException e) {
			return e.getTypeMirror();
		}

		//Never happened;
		return null;
	}

	public static TypeMirror viewApiValue(RiseModule riseModule) {
		try {
			riseModule.viewApi();
		} catch (MirroredTypeException e) {
			return e.getTypeMirror();
		}

		//Never happened;
		return null;
	}

	public static TypeMirror parentValue(RiseModule riseModule) {
		try {
			riseModule.parent();
		} catch (MirroredTypeException e) {
			return e.getTypeMirror();
		}

		//Never happened;
		return null;
	}

	public static TypeMirror gateKeeperValue(Presenter presenter) {
		try {
			presenter.useGateKeeper();
		} catch (MirroredTypeException e) {
			return e.getTypeMirror();
		}

		//Never happened;
		return null;
	}
}
