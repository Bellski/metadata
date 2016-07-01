package ru.bellski.lang.metasql.actions;

import com.intellij.codeInsight.CodeInsightUtil;
import com.intellij.codeInsight.daemon.QuickFixBundle;
import com.intellij.codeInsight.hint.QuestionAction;
import com.intellij.ide.util.DefaultPsiElementCellRenderer;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiReference;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.statistics.JavaStatisticsManager;
import com.intellij.psi.statistics.StatisticsManager;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.ui.popup.list.ListPopupImpl;
import com.intellij.ui.popup.list.PopupListElementRenderer;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import ru.bellski.lang.metasql.MetaSqlFile;
import ru.bellski.lang.metasql.psi.MetaSqlImportList;
import ru.bellski.lang.metasql.util.MetaSqlElementGenerator;


import javax.swing.*;
import java.awt.*;

/**
 * Created by oem on 6/22/16.
 */
public class AddImportQuestionAction implements QuestionAction {

    private final Project myProject;
    private final PsiReference myReference;
    private final PsiClass[] myTargetClasses;
    private final Editor myEditor;
    private final MetaSqlFile metaSqlFile;

    public AddImportQuestionAction(@NotNull Project project, @NotNull PsiReference ref, @NotNull Editor editor, @NotNull MetaSqlFile metaSqlFile, @NotNull PsiClass... targetClasses) {

        myProject = project;
        myReference = ref;
        myTargetClasses = targetClasses;
        myEditor = editor;
        this.metaSqlFile = metaSqlFile;
    }

    @Override
    public boolean execute() {
        PsiDocumentManager.getInstance(myProject).commitAllDocuments();

        if (!myReference.getElement().isValid()) {
            return false;
        }

        for (PsiClass myTargetClass : myTargetClasses) {
            if (!myTargetClass.isValid()) {
                return false;
            }
        }

        if (myTargetClasses.length == 1) {
            addImport(myReference, myTargetClasses[0]);
        } else {
            chooseClassAndImport();
        }
        return true;
    }

    private void addImport(PsiReference myReference, PsiClass myTargetClass) {
        StatisticsManager.getInstance().incUseCount(JavaStatisticsManager.createInfo(null, myTargetClass));
        CommandProcessor.getInstance().executeCommand(myProject, new Runnable() {
            @Override
            public void run() {
                ApplicationManager.getApplication().runWriteAction(new Runnable() {
                    @Override
                    public void run() {
                        DumbService.getInstance(myProject).withAlternativeResolveEnabled(new Runnable() {
                            @Override
                            public void run() {
                                _addImport(myReference, myTargetClass);
                            }
                        });
                    }
                });
            }
        }, QuickFixBundle.message("add.import"), null);
    }

    private void _addImport(PsiReference ref, PsiClass target) {
        final PsiDocumentManager manager = PsiDocumentManager.getInstance(myProject);
        final Document document = manager.getDocument(metaSqlFile);

        if (document != null) {
            manager.commitDocument(document);
        }


        final MetaSqlImportList importList = PsiTreeUtil.findChildOfAnyType(metaSqlFile, MetaSqlImportList.class);

        importList.add(MetaSqlElementGenerator.createImportStatementFromText(myProject, target.getQualifiedName()));

        CodeStyleManager.getInstance(myProject).reformat(metaSqlFile);
    }

    protected void bindReference(PsiReference ref, PsiClass targetClass) {
        ref.bindToElement(targetClass);
    }

    private void chooseClassAndImport() {
        CodeInsightUtil.sortIdenticalShortNamedMembers(myTargetClasses, myReference);

        final BaseListPopupStep<PsiClass> step = new BaseListPopupStep<PsiClass>(QuickFixBundle.message("class.to.import.chooser.title"), myTargetClasses) {
            @Override
            public boolean isAutoSelectionEnabled() {
                return false;
            }

            @Override
            public boolean isSpeedSearchEnabled() {
                return true;
            }


            @Override
            public boolean hasSubstep(PsiClass selectedValue) {
                return false;
            }

            @NotNull
            @Override
            public String getTextFor(PsiClass value) {
                return ObjectUtils.assertNotNull(value.getQualifiedName());
            }

            @Override
            public Icon getIconFor(PsiClass aValue) {
                return aValue.getIcon(0);
            }

            @Override
            public PopupStep onChosen(PsiClass selectedValue, boolean finalChoice) {
                PsiDocumentManager.getInstance(myProject).commitAllDocuments();
                addImport(myReference, selectedValue);
                return FINAL_CHOICE;
            }
        };
        ListPopupImpl popup = new ListPopupImpl(step) {
            @Override
            protected ListCellRenderer getListElementRenderer() {
                final PopupListElementRenderer baseRenderer = (PopupListElementRenderer) super.getListElementRenderer();
                final DefaultPsiElementCellRenderer psiRenderer = new DefaultPsiElementCellRenderer();
                return new ListCellRenderer() {
                    @Override
                    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                        JPanel panel = new JPanel(new BorderLayout());
                        baseRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                        panel.add(baseRenderer.getNextStepLabel(), BorderLayout.EAST);
                        panel.add(psiRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus));
                        return panel;
                    }
                };
            }
        };
        popup.showInBestPositionFor(myEditor);
    }
}
