package org.vaadin.rise.plugin.idea;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPackage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.vaadin.rise.plugin.idea.model.*;
import org.vaadin.rise.plugin.idea.utils.PackageUtilExt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by oem on 8/3/16.
 */
public class CreatePresenterForm extends DialogWrapper{

    private JPanel contentPane;
    private JTextField name;
    private JRadioButton placePresenterRadioButton;
    private JRadioButton presenterComponentRadioButton;
    private JRadioButton popupPresenterRadioButton;
    private JTextField nameToken;
    private JTextField packageName;
    private JRadioButton nestedPresenterRadioButton;
    private JComboBox<String> slotList;

    private final Project project;

    @NotNull
    private AnActionEvent anEvent;
    private RiseModuleModel riseModuleModel;

    protected CreatePresenterForm(RiseModuleModel riseModuleModel, SlotListModel slotListModel, @NotNull AnActionEvent anEvent) {
        super(anEvent.getProject());
        this.riseModuleModel = riseModuleModel;

        this.project = anEvent.getProject();
        this.anEvent = anEvent;

        slotList.setModel(slotListModel);

        slotList.setSelectedIndex(0);

        initDefaults();
        initHandlers();

        init();
    }

    private void initDefaults() {
        nameToken.setEnabled(true);
        nameToken.grabFocus();



        packageName.setText(riseModuleModel.getPackageName());

        placePresenterRadioButton.setSelected(true);
    }

    private void initHandlers() {
        initRadioHandlers();

    }

    private void initRadioHandlers() {
        nestedPresenterRadioButton.addActionListener(e -> {
            if (nestedPresenterRadioButton.isSelected()) {
                riseModuleModel
                        .getPresenterModel()
                        .setPresenterType(PresenterType.NESTED_PRESENTER);

                riseModuleModel
                        .getApiInterfaceModel()
                        .setPopup(false);

                riseModuleModel
                        .getRiseViewModel()
                        .setPopup(false);

                nameToken.setEnabled(false);
                slotList.setEnabled(true);
            }
        });


        placePresenterRadioButton.addActionListener(e -> {
            if (placePresenterRadioButton.isSelected()) {
                riseModuleModel
                        .getPresenterModel()
                        .setPresenterType(PresenterType.PLACE_PRESENTER);

                riseModuleModel
                        .getApiInterfaceModel()
                        .setPopup(false);

                riseModuleModel
                        .getRiseViewModel()
                        .setPopup(false);

                nameToken.setEnabled(true);
                slotList.setEnabled(true);
            }
        });

        presenterComponentRadioButton.addActionListener(e -> {
            if (presenterComponentRadioButton.isSelected()) {
                riseModuleModel
                        .getPresenterModel()
                        .setPresenterType(PresenterType.PRESENTER_COMPONENT);

                riseModuleModel
                        .getApiInterfaceModel()
                        .setPopup(false);

                riseModuleModel
                        .getRiseViewModel()
                        .setPopup(false);

                nameToken.setEnabled(false);
                slotList.setEnabled(false);
            }
        });

        popupPresenterRadioButton.addActionListener(e -> {
            if (popupPresenterRadioButton.isSelected()) {
                riseModuleModel
                        .getPresenterModel()
                        .setPresenterType(PresenterType.POPUP_PRESENTER);

                riseModuleModel
                        .getApiInterfaceModel()
                        .setPopup(true);

                riseModuleModel
                        .getRiseViewModel()
                        .setPopup(true);

                nameToken.setEnabled(false);
                slotList.setEnabled(false);
            }
        });
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return contentPane;
    }

    @Override
    protected void doOKAction() {
        super.doOKAction();

        riseModuleModel.setName(name.getText());
        riseModuleModel.getPresenterModel().setNameToken(nameToken.getText());
        riseModuleModel.setSlotItem((SlotItem) slotList.getSelectedItem());
    }
}
