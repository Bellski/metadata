package ru.bellski.metasql.lang.psi;

import com.intellij.psi.PsiClass;
import com.intellij.psi.impl.source.resolve.ResolveCache;

/**
 * Created by oem on 6/8/16.
 */
public interface MetaSqlMetadataReferenceElement extends MetaSqlReferenceElement{
    PsiClass[] multyResolve();
}
