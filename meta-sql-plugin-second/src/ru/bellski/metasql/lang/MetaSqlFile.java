package ru.bellski.metasql.lang;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.FileContentUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import ru.bellski.metasql.lang.psi.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by oem on 5/19/16.
 */
public class MetaSqlFile extends PsiFileBase {

    protected MetaSqlFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, MetaSqlLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return MetaSqlFileType.INSTANCE;
    }

    @Override
    public void subtreeChanged() {
        super.subtreeChanged();
//        ApplicationManager
//                .getApplication()
//                .invokeLater(() ->
//                        FileContentUtil.reparseFiles(InjectedLanguageUtil.findInjectionHost(MetaSqlFile.this).getContainingFile().getVirtualFile())
//                );
    }

    @Override
    public void delete() throws IncorrectOperationException {
        super.delete();
    }

    public MetaSqlRoot getMetaSqlRoot() {
        return (MetaSqlRoot) getNode().findChildByType(MetaSqlTokenTypes.ROOT);
    }
}
