package ru.bellski.metasql;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.impl.search.AllClassesSearchExecutor;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.util.containers.HashMap;
import com.intellij.util.containers.HashSet;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by Aleksandr on 14.05.2016.
 */
public class SqlMetadataJavaClassCache {
    private final Map<String, PsiClass> classByFqn = new HashMap<>();

    public SqlMetadataJavaClassCache(@NotNull Project project) {

        ApplicationManager.getApplication().runReadAction(new Runnable() {
            @Override
            public void run() {
                final Set<String> names = new HashSet<>();

                AllClassesSearchExecutor.processClassNames(project, GlobalSearchScope.allScope(project), name -> {
                    if (name.endsWith("SqlMetadata")) {
                        names.add(name);
                    }
                });

                names.forEach(name -> {
                    final PsiClass[] classes = PsiShortNamesCache.getInstance(project).getClassesByName(name, GlobalSearchScope.allScope(project));

                    for (PsiClass aClass : classes) {
                        final PsiModifierList ml = aClass.getModifierList();

                        if (ml != null) {
                            final PsiAnnotation[] annotations = ml.getAnnotations();

                            if (annotations.length == 1 && annotations[0].getQualifiedName().equals("ru.bellski.metadata.SqlMetadata")) {
                                classByFqn.put(aClass.getQualifiedName(), aClass);
                            }
                        }

                    }
                });
            }
        });
    }

    public static SqlMetadataJavaClassCache getInstance(@NotNull Project project) {
        return ServiceManager.getService(project, SqlMetadataJavaClassCache.class);
    }

    public PsiClass findPsiClassByFqn(String fqn) {
        return classByFqn.get(fqn);
    }

    public Collection<PsiClass> getAllSqlMetaClasses() {
        return classByFqn.values();
    }
}
