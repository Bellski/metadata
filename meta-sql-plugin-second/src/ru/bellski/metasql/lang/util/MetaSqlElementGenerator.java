package ru.bellski.metasql.lang.util;

import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.impl.PsiFileFactoryImpl;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiTypesUtil;
import com.intellij.testFramework.LightVirtualFile;
import ru.bellski.metasql.lang.MetaSqlFileType;
import ru.bellski.metasql.lang.MetaSqlLanguage;
import ru.bellski.metasql.lang.psi.MetaSqlImportStatement;

/**
 * Created by oem on 6/22/16.
 */
public class MetaSqlElementGenerator {

    public static MetaSqlImportStatement createImportStatementFromText(Project myProject, String importName) {
        final PsiFile dummyFile = createDummyFile(myProject, "import " + importName + ";");
        return PsiTreeUtil.findChildOfType(dummyFile, MetaSqlImportStatement.class);
    }

    public static PsiWhiteSpace createWhiteSpace(Project myProject) {
        final PsiFile dummyFile = createDummyFile(myProject, "");
        return PsiTreeUtil.findChildOfType(dummyFile, PsiWhiteSpace.class);
    }


    public static PsiFile createDummyFile(Project myProject, String text) {
        final PsiFileFactory factory = PsiFileFactory.getInstance(myProject);
        final String name = "dummy." + MetaSqlFileType.INSTANCE.getDefaultExtension();
        final LightVirtualFile virtualFile = new LightVirtualFile(name, MetaSqlFileType.INSTANCE, text);
        final PsiFile psiFile = ((PsiFileFactoryImpl)factory).trySetupPsiForFile(virtualFile, MetaSqlLanguage.INSTANCE, false, true);
        assert psiFile != null;
        return psiFile;
    }
}
