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
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import ru.bellski.metadata.MetaProperty;
import ru.bellski.metadata.Metadata;
import ru.bellski.metadata.maven.forgeneration.GenerateMetaProperty;
import ru.bellski.metadata.maven.forgeneration.GeneratedMetadata;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

	@Parameter(
		property = "generate-metadata.generateSqlMetadata",
		required = true
	)
	private Boolean generateSqlMetadata = false;


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

				final Map<Class<?>, GeneratedMetadata<?>> result = generateAndWriteMetadata();

				if (generateSqlMetadata) {
					generateAndWriteSqlMetadata(result);
				}

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

	private Map<Class<?>, GeneratedMetadata<?>> generateAndWriteMetadata() throws IOException {
		final ClassLoader cl = URLClassLoader.newInstance(new URL[] {new File(project.getBuild().getOutputDirectory()).toURI().toURL()});

		final Set<Class<?>> candidates = GenerateMetadataFinder.findCandidates(domain, cl);
		final Map<Class<?>, GeneratedMetadata<?>> metadataByType = new HashMap<>();

		for (Class<?> candidate : candidates) {
			final MetadataGeneratorResult result = MetadataGenerator.generate(candidate, candidates);
			final JavaClassSource metadataClass = result.getMetadataClass();
			final JavaInterfaceSource propertiesClass = result.getMetadataProperties();

			Files.createDirectories(Paths.get(generatedSources.getPath() + "/" + convertPackageToPath(candidate.getPackage().getName())));

			Files.write(
				Paths.get(generatedSources.getPath() + "/" + convertQualifiedNameToPath(metadataClass.getQualifiedName())),
				metadataClass.toString().getBytes()
			);

			Files.write(
				Paths.get(generatedSources.getPath() + "/" + convertQualifiedNameToPath(propertiesClass.getQualifiedName())),
				propertiesClass.toString().getBytes()
			);

			metadataByType.put(candidate, result.getGeneratedMetadata());
		}

		return metadataByType;
	}

	private void generateAndWriteSqlMetadata(Map<Class<?>, GeneratedMetadata<?>> result) throws IOException {
		for (GeneratedMetadata<?> generatedMetadata : result.values()) {
			for (MetaProperty<?, ?> metaProperty : generatedMetadata.getProperties()) {
				if (metaProperty.isNested()) {
					((GenerateMetaProperty) metaProperty).setMetadata(result.get(metaProperty.getType()));
				}
			}
		}

		for (GeneratedMetadata<?> generatedMetadata : result.values()) {
			final JavaClassSource sqlMetadataClass = SQLMetadataGenerator.generate(generatedMetadata);

			Files.write(
				Paths.get(generatedSources.getPath() + "/" + convertQualifiedNameToPath(sqlMetadataClass.getQualifiedName())),
				sqlMetadataClass.toString().getBytes()
			);
		}
	}

	private String convertPackageToPath(String packageName) {
		return packageName.replaceAll("\\.", "/");
	}

	private String convertQualifiedNameToPath(String qualifiedName) {
		return qualifiedName.replaceAll("\\.", "/").concat(".java");
	}

	private ClassLoader buildingClassLoader() throws MalformedURLException {
		return URLClassLoader.newInstance(new URL[] {classes.toURI().toURL()});
	}
}
