package ru.bellski.metadata.maven;

import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import ru.bellski.metadata.maven.forgeneration.GeneratedMetadata;

/**
 * Created by oem on 5/10/16.
 */
public class MetadataGeneratorResult {
    private JavaClassSource metadataClass;
    private JavaInterfaceSource metadataProperties;
    private GeneratedMetadata generatedMetadata;

    public JavaClassSource getMetadataClass() {
        return metadataClass;
    }

    void setMetadataClass(JavaClassSource metadataClass) {
        this.metadataClass = metadataClass;
    }

    public JavaInterfaceSource getMetadataProperties() {
        return metadataProperties;
    }

    void setMetadataProperties(JavaInterfaceSource metadataProperties) {
        this.metadataProperties = metadataProperties;
    }

    @Override
    public String toString() {
        return "MetadataGeneratorResult{" +
                "metadataClass=" + metadataClass +
                ", metadataProperties=" + metadataProperties +
                '}';
    }

    void setGeneratedMetadata(GeneratedMetadata generatedMetadata) {
        this.generatedMetadata = generatedMetadata;
    }

    public GeneratedMetadata getGeneratedMetadata() {
        return generatedMetadata;
    }
}
