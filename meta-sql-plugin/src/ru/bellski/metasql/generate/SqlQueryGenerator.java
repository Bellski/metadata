package ru.bellski.metasql.generate;

import com.intellij.openapi.vfs.VfsUtil;
import org.jetbrains.idea.maven.project.MavenProject;

import java.io.IOException;

/**
 * Created by oem on 5/26/16.
 */
public class SqlQueryGenerator {
    public static void generate(MavenProject project) {
        String genDir = project.getGeneratedSourcesDirectory(false);

        try {
            VfsUtil.createDirectories(genDir + "/meta/queries");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
