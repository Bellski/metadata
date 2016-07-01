package ru.bellski.metadata.maven.sqlmetadata;

/**
 * Created by oem on 6/30/16.
 */
public interface BodyBuilder {
	void onBuild();
	BodyBuilder append(String string);
	BodyBuilder buildNestedBeanInstance(Class type, String name);
	BodyBuilder buildSetterMethod(String propertyVariableName, String propertyName, String propertyFieldPath, Class propertyType);
}
