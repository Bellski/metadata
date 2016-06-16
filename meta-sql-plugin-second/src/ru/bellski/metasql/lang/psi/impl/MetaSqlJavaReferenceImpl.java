package ru.bellski.metasql.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
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
        supportedReferenceByName.put("List", "java.util");
        supportedReferenceByName.put("Boolean", "java.lang");
        supportedReferenceByName.put("String", "java.lang");
        supportedReferenceByName.put("Long", "java.lang");
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
        return null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }
}
