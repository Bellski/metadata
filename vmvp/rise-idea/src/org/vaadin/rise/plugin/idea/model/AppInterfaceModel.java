package org.vaadin.rise.plugin.idea.model;

/**
 * Created by oem on 8/3/16.
 */
public class AppInterfaceModel extends PsiClassModel {
    private boolean isPopup;

    public AppInterfaceModel(String packageName) {
        super(packageName);
    }

    public void setPopup(boolean popup) {
        isPopup = popup;
    }

    public boolean isPopup() {
        return isPopup;
    }
}
