package ru.bellski.metasql.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.util.containers.HashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bellski.metasql.lang.psi.MetaSqlJavaReference;

import java.util.Map;

/**
 * Created by oem on 6/16/16.
 */
public class MetaSqlJavaReferenceImpl extends MetaSqlReferenceElementImpl implements MetaSqlJavaReference {
    public static Map<String, String> supportedReferenceByName = new HashMap<>();

    static {
        supportedReferenceByName.put("List", "java.util.List");
        supportedReferenceByName.put("Boolean", "java.lang.Boolean");
        supportedReferenceByName.put("String", "java.lang.String");
        supportedReferenceByName.put("Long", "java.lang.Long");
    }

    public MetaSqlJavaReferenceImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getPackageName() {
        return supportedReferenceByName.get(getText());
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        PsiClass[] result = PsiShortNamesCache.getInstance(getProject()).getClassesByName(getText(), GlobalSearchScope.allScope(getProject()));

        PsiClass jClass = null;
        for (PsiClass psiClass : result) {
            if (psiClass.getQualifiedName().equals(supportedReferenceByName.get(getText()))) {
                jClass = psiClass;
                break;
            }
        }
        return jClass;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }

    @Override
    public PsiReference getReference() {
        return this;
    }
}
