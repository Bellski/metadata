package ru.bellski.metasql.lang;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.Language;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.event.DocumentAdapter;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.FileContentUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;

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
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                FileContentUtil.reparseFiles(getProject(), Arrays.asList(InjectedLanguageUtil.findInjectionHost(MetaSqlFile.this).getContainingFile().getVirtualFile()), true);
            }
        });
    }

    @Override
    public void delete() throws IncorrectOperationException {
        super.delete();
    }


}
