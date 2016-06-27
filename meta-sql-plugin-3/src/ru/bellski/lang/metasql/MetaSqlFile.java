package ru.bellski.lang.metasql;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

/**
 * Created by oem on 5/19/16.
 */
public class MetaSqlFile extends PsiFileBase {

    private boolean hasErrors;

    protected MetaSqlFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, MetaSqlLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return MetaSqlFileType.INSTANCE;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }
}
