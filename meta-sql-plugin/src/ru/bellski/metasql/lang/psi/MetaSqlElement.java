package ru.bellski.metasql.lang.psi;

import com.intellij.psi.NavigatablePsiElement;

/**
 * Created by oem on 5/23/16.
 */
public interface MetaSqlElement extends NavigatablePsiElement {
    void accept(final MetaSqlVisitor visitor);
    void acceptChildren(final MetaSqlVisitor visitor);
}
