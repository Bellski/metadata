package ru.bellski.metasql.lang;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiClass;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

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
}
