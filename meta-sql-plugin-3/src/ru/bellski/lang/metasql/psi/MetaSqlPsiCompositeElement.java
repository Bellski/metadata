package ru.bellski.lang.metasql.psi;

import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.tree.IElementType;

/**
 * Created by oem on 6/24/16.
 */
public interface MetaSqlPsiCompositeElement extends NavigatablePsiElement {
    IElementType getTokenType();
}
