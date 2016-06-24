package ru.bellski.lang.metasql.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

/**
 * Created by oem on 6/24/16.
 */
public interface MetaSqlPsiImportStatementBase extends PsiElement {
    @NotNull
    MetaSqlImportReferenceElement getImportReferenceElement();
}
