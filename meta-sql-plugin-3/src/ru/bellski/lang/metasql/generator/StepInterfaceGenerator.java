package ru.bellski.lang.metasql.generator;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.codeStyle.CodeStyleManager;
import ru.bellski.lang.metasql.MetaSqlFile;
import ru.bellski.lang.metasql.psi.MetaSqlParameterVariable;

import java.util.List;

/**
 * Created by oem on 6/29/16.
 */
public class StepInterfaceGenerator {
    public static PsiClass[] generate(MetaSqlFile metaSqlFile, PsiClass executorInterface) {
        PsiClass[] steps = null;

        final Project project = metaSqlFile.getProject();
        final PsiElementFactory elementFactory = PsiElementFactory.SERVICE.getInstance(project);
        final JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(project);

        final List<MetaSqlParameterVariable> parameters = metaSqlFile.getParameters();

        if (parameters != null) {
            steps = new PsiClass[metaSqlFile.getParamsCount()];

            for (int i = 0; i < parameters.size(); i++) {
                final MetaSqlParameterVariable parameter = parameters.get(i);

                final PsiClass stepInterface = elementFactory
                        .createInterface(
                                metaSqlFile.getQueryName()
                                + StringUtil.capitalize(parameter.getIdentifier().getText())
                        );

                if (i != parameters.size() -1) {
                    stepInterface.add(createSetterMethod(elementFactory, parameter, StringUtil.capitalize(parameters.get(i+1).getIdentifier().getText())));
                } else {
                    stepInterface.add(createSetterMethod(elementFactory, parameter, executorInterface.getName()));
                }

                steps[i] = stepInterface;
            }
        }

        return steps == null ? new PsiClass[0] : steps;
    }

    private static PsiMethod createSetterMethod(PsiElementFactory elementFactory, MetaSqlParameterVariable currentParameter, String nextStepName) {
        final PsiMethod setterMethod = elementFactory.createMethodFromText(nextStepName + " set" + StringUtil.capitalize(currentParameter.getIdentifier().getText()) + "();", null);
        setterMethod
                .getParameterList()
                .add(elementFactory.createParameterFromText(currentParameter.getTypeElement().getText() + " value", null));

        return setterMethod;
    }
}
