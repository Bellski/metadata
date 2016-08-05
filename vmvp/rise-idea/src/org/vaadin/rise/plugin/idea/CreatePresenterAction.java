package org.vaadin.rise.plugin.idea;

import com.intellij.codeInsight.template.impl.ShortenFQNamesProcessor;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.ide.util.FQNameCellRenderer;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.impl.java.stubs.index.JavaShortClassNameIndex;
import com.intellij.psi.impl.source.PsiJavaFileBaseImpl;
import com.intellij.psi.impl.source.tree.java.PsiArrayInitializerExpressionImpl;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.psi.search.searches.AnnotatedMembersSearch;
import com.intellij.psi.search.searches.ClassInheritorsSearch;
import com.intellij.psi.util.FindClassUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.Processor;
import com.intellij.util.indexing.FileBasedIndex;
import com.intellij.util.indexing.FileBasedIndexExtension;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.jetbrains.annotations.NotNull;
import org.vaadin.rise.plugin.idea.generator.ApiInterfaceGenerator;
import org.vaadin.rise.plugin.idea.generator.PresenterGenerator;
import org.vaadin.rise.plugin.idea.generator.RiseModuleGenerator;
import org.vaadin.rise.plugin.idea.generator.ViewGenerator;
import org.vaadin.rise.plugin.idea.model.PsiClassModel;
import org.vaadin.rise.plugin.idea.model.RiseModuleModel;
import org.vaadin.rise.plugin.idea.model.SlotItem;
import org.vaadin.rise.plugin.idea.model.SlotListModel;
import org.vaadin.rise.plugin.idea.utils.PackageUtilExt;
import org.vaadin.rise.slot.annotation.Slot;
import org.vaadin.rise.slot.api.IsNested;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oem on 8/3/16.
 */
public class CreatePresenterAction extends AnAction {
    private Configuration freeMarkerCfg;

    private ApiInterfaceGenerator psiApiInterfaceGenerator;
    private ViewGenerator psiViewGenerator;
    private PresenterGenerator psiPresenterGenerator;
    private RiseModuleGenerator psiRiseModuleGenerator;


    public CreatePresenterAction() {
        super("Create Presenter", "Create Rise Presenter", null);

        freeMarkerCfg = new Configuration(Configuration.VERSION_2_3_0);
        freeMarkerCfg.setClassForTemplateLoading(this.getClass(), "/template");
        freeMarkerCfg.setDefaultEncoding("UTF-8");
        freeMarkerCfg.setLogTemplateExceptions(false);


        psiApiInterfaceGenerator = new ApiInterfaceGenerator(freeMarkerCfg);
        psiViewGenerator = new ViewGenerator(freeMarkerCfg);
        psiPresenterGenerator = new PresenterGenerator(freeMarkerCfg);
        psiRiseModuleGenerator = new RiseModuleGenerator(freeMarkerCfg);
    }


    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        final Module module = anActionEvent.getData(LangDataKeys.MODULE);

        if (module == null) {
            Messages.showErrorDialog("You must choose a package inside a valid module.", "Invalid Module");
            return;
        }

        final PsiFileFactory psiFileFactory = PsiFileFactory.getInstance(module.getProject());
        final PsiElementFactory elemFactory = JavaPsiFacade.getElementFactory(module.getProject());

        final PsiPackage selectedPackageRoot = PackageUtilExt
                .getSelectedPackageRoot(
                        module.getProject(),
                        anActionEvent.getData(LangDataKeys.PSI_ELEMENT)
                );

        final SlotListModel slotListModel = new SlotListModel(findAllSlots(module.getProject()));
        final RiseModuleModel riseModuleModel = new RiseModuleModel(selectedPackageRoot.getQualifiedName());

        final CreatePresenterForm dialog = new CreatePresenterForm(riseModuleModel, slotListModel, anActionEvent);

        if (!dialog.showAndGet()) {
            return;
        }

        if (dialog.isOK()) {
            final PsiFile apiInterface;
            final PsiFile psiView;
            final PsiFile psiPresenter;
            final PsiFile psiRiseModule;

            try {
                apiInterface = psiApiInterfaceGenerator.generate(riseModuleModel.getApiInterfaceModel(), psiFileFactory);
                psiView = psiViewGenerator.generate(riseModuleModel.getRiseViewModel(), psiFileFactory);
                psiPresenter = psiPresenterGenerator.generate(riseModuleModel.getPresenterModel(), psiFileFactory);
                psiRiseModule = psiRiseModuleGenerator.generate(riseModuleModel, psiFileFactory);
            } catch (IOException | TemplateException e) {
                throw new RuntimeException(e);
            }

            ApplicationManager
                    .getApplication()
                    .invokeAndWait(() -> new WriteCommandAction<Void>(module.getProject()) {

                        @Override
                        protected void run(@NotNull Result<Void> result) throws Throwable {
                            final PsiDirectory dir = PsiManager
                                    .getInstance(module.getProject())
                                    .findDirectory(anActionEvent.getData(LangDataKeys.VIRTUAL_FILE));

                            final CodeStyleManager codeStyle = CodeStyleManager.getInstance(module.getProject());

                            dir.add(codeStyle.reformat(apiInterface));
                            dir.add(codeStyle.reformat(psiView));
                            dir.add(codeStyle.reformat(psiPresenter));
                            dir.add(codeStyle.reformat(psiRiseModule));

                            final SlotItem slotItem = riseModuleModel.getSlotItem();

                            final PsiClass psiRiseModuleClass = ((PsiJavaFile) psiRiseModule).getClasses()[0];

                            final PsiAnnotation moduleAnnotation = slotItem.getParentRiseModule().getModifierList().findAnnotation("dagger.Module");

                            final PsiAnnotationParameterList moduleAnnotationParameterList = moduleAnnotation.getParameterList();

                            PsiArrayInitializerMemberValue includesAttribute = (PsiArrayInitializerMemberValue) moduleAnnotation.findDeclaredAttributeValue("includes");


                            if (includesAttribute == null) {
                                moduleAnnotation.setDeclaredAttributeValue("includes", elemFactory.createExpressionFromText("{}", null));
                                includesAttribute = (PsiArrayInitializerMemberValue) moduleAnnotation.findDeclaredAttributeValue("includes");
                            }


                            includesAttribute.addBefore(
                                    elemFactory.createExpressionFromText(psiRiseModuleClass.getQualifiedName()+".class", null),
                                    includesAttribute.getLastChild()
                            );
                        }
                    }.execute(), ModalityState.NON_MODAL);

        }

    }

    private List<SlotItem> findAllSlots(Project project) {
        List<SlotItem> slots = new ArrayList<>();

        GlobalSearchScope scope = GlobalSearchScope.allScope(project);

        PsiClass nestedSlotClass = JavaPsiFacade.getInstance(project)
                .findClass(Slot.class.getName(), scope);


        AnnotatedMembersSearch.search(nestedSlotClass,
                GlobalSearchScope.allScope(project)).forEach(new Processor<PsiMember>() {
            public boolean process(PsiMember psiMember) {
                PsiClass slot = (PsiClass) psiMember;

                final PsiClass implementedSlot = ClassInheritorsSearch.search(slot).findFirst();

                if (implementedSlot != null) {
                    final PsiClass parentRiseModule = (PsiClass) implementedSlot.getParent();

                    final PsiClassModel slotPsiClassModel = new PsiClassModel(
                            slot.getName(),
                            getPackage(slot.getQualifiedName())
                    );

                    final PsiClassModel parentModule = new PsiClassModel(
                            parentRiseModule.getName(),
                            getPackage(parentRiseModule.getQualifiedName())
                    );


                    slots.add(new SlotItem(slotPsiClassModel, parentModule, parentRiseModule));
                }



                return true;
            }
        });

        return slots;
    }


    private String getPackage(String fqn) {
        return fqn.substring(0, fqn.lastIndexOf('.'));
    }
}
