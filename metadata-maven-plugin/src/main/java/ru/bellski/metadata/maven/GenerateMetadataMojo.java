package ru.bellski.metadata.maven;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by oem on 4/27/16.
 */
@Mojo(
	name = "generate-metadata",
	defaultPhase = LifecyclePhase.COMPILE,
	requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME
)
public class GenerateMetadataMojo extends AbstractMojo {

	@Parameter(
		required = true,
		property = "project"
	)
	private MavenProject project;

	@Parameter(
		required = true,
		property = "meta.generatedSources",
		defaultValue = "${project.build.directory}/generated-sources/meta"
	)
	private File generatedSources;

	@Parameter(
		required = true,
		property = "meta.classes",
		defaultValue = "${project.build.outputDirectory}"
	)
	private File classes;

	@Parameter(
		required = true,
		property = "meta.source",
		defaultValue = "${project.basedir}/src/main/java"
	)
	private File source;

	@Parameter(
		property = "generate-metadata.domain",
		required = true
	)
	private String domain = "";


	public void execute() throws MojoExecutionException, MojoFailureException {
        if (!generatedSources.exists()) {
            try {
                creatingDirectories();

                GenerateMetadataCompiler.
                        compile(
                                source.toPath(),
                                domain,
                                classes.toPath(),
                                collectCompileClassPath()
                        );
            } catch (Exception e) {
                throw new MojoFailureException(e.getMessage(), e);
            }
        }

		project.addCompileSourceRoot(generatedSources.getAbsolutePath());
	}

	private void creatingDirectories() throws IOException {
		Files
			.createDirectories(
				Paths
					.get(
						project
							.getBuild()
							.getOutputDirectory()
					)
			);

		Files
			.createDirectories(
				generatedSources.toPath()
			);
	}

	private List<File> collectCompileClassPath() throws DependencyResolutionRequiredException {
		return (List<File>) project
			.getCompileClasspathElements()
			.stream()
			.map(classPath -> new File((String) classPath))
			.collect(Collectors.toList());
	}

	private ClassLoader buildingClassLoader() throws MalformedURLException {
		return URLClassLoader.newInstance(new URL[] {classes.toURI().toURL()});
	}
}
