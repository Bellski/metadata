package ru.bellski.metadata.maven;

import com.google.common.collect.Sets;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

/**
 * Created by oem on 4/27/16.
 */
public class GenerateMetadataCompiler {
    public static void compile(Path source, String aPackage, Path classes, List<File> dependencies) throws IOException {
        final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        final StandardJavaFileManager fm = compiler.getStandardFileManager(null, null, null);

        fm.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(classes.toFile()));
        fm.setLocation(StandardLocation.SOURCE_PATH, Collections.singletonList(source.toFile()));
        fm.setLocation(StandardLocation.CLASS_PATH, dependencies);


        Iterable<JavaFileObject> javaFiles = fm.list(StandardLocation.SOURCE_PATH, aPackage, Sets.newHashSet(JavaFileObject.Kind.SOURCE), true);

        if (!javaFiles.iterator().hasNext()) {
            throw new RuntimeException("Source files not found");
        }

        compiler.getTask(null, fm, null, null, null, javaFiles).call();
    }
}
