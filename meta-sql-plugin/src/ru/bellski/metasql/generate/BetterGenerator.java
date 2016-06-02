package ru.bellski.metasql.generate;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.util.PsiTreeUtil;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jetbrains.idea.maven.project.MavenProject;
import ru.bellski.metasql.lang.psi.MetaSqlBody;
import ru.bellski.metasql.lang.psi.MetaSqlParameter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Aleksandr on 30.05.2016.
 */
public class BetterGenerator {
    public static void generate(String name, String packageName, String sqlQuery, MetaSqlBody metaSqlBody, MavenProject mProject, Project project) {
        QueryClass
                .create(name, packageName, sqlQuery)
                .addParamInterfaces(
                        buildParamInterfaces(metaSqlBody, name, packageName)
                )
                .addExecutionStepInterface(
                        ExecutionStepInterface.create(name, packageName)
                )
                .save(mProject.getDirectoryFile(), project);
    }

    private static List<ParameterInterface> buildParamInterfaces(MetaSqlBody metaSqlBody, String queryClassName, String packageName) {
        final Collection<MetaSqlParameter> parameters = PsiTreeUtil.findChildrenOfType(metaSqlBody, MetaSqlParameter.class);

        final List<ParameterInterface> paramInterfaces = new ArrayList<>();

        if (parameters.size() > 0) {
            final MetaSqlParameter[] parametersArray = parameters
                    .stream()
                    .toArray(MetaSqlParameter[]::new);

            for (int i = 0; i < parametersArray.length; i++) {
                final MetaSqlParameter metaSqlParameter = parametersArray[i];
                final ParameterInterface parameterInterface = ParameterInterface.create(
                        buildParamInterfaceName(metaSqlParameter),
                        packageName
                );

                if (i != parametersArray.length -1) {
                    parameterInterface
                            .createSetter()
                            .setReturnType(buildParamInterfaceName(parametersArray[i + 1]))
                            .setName(buildParamSetter(metaSqlParameter))
                            .setParameter(metaSqlParameter.getParameterType().getText());
                } else {
                    parameterInterface
                            .createSetter()
                            .setReturnType(queryClassName + "Execution")
                            .setName(buildParamSetter(metaSqlParameter))
                            .setParameter(metaSqlParameter.getParameterType().getText());
                }

                paramInterfaces.add(parameterInterface);
            }
        }

        return paramInterfaces;
    }

    private static String buildParamInterfaceName(MetaSqlParameter metaSqlParameter) {
        return
                "Set"
                        + StringUtil.wordsToBeginFromUpperCase(metaSqlParameter.getParameterKeyword().getText())
                        + "Step";
    }

    private static String buildParamSetter(MetaSqlParameter metaSqlParameter) {
        return "set"
                + StringUtil.wordsToBeginFromUpperCase(metaSqlParameter.getParameterKeyword().getText());
    }

    private static String buildExecutionMethod(MetaSqlParameter metaSqlParameter, JavaInterfaceSource executionInterface) {
        return
                executionInterface.getName()
                        + " set"
                        + StringUtil.wordsToBeginFromUpperCase(metaSqlParameter.getParameterKeyword().getText())
                        + "("
                        + metaSqlParameter.getParameterType().getText()
                        + " value" + ");";
    }

    private static String normalizeClassSource(String source) {
        return source.replaceAll("\\r", "");
    }
}
