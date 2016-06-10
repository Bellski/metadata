package ru.bellski.metasql.lang.psi;

import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiReference;

/**
 * Created by oem on 6/8/16.
 */
public interface MetaSqlReferenceElement extends PsiReference, MetaSqlCompositeElement, NavigatablePsiElement {
}
