package ru.bellski.metadata.yet;

import java.util.Map;

/**
 * Created by oem on 5/4/16.
 */
public class SQLUnmarshaller {
	public static <TYPE> TYPE unmarshall(Map<String, Object> row, Metadata<TYPE> metadata) {
		TYPE type = metadata.create();

		for (String pName : row.keySet()) {
			MetaProperty property = null;
			Object value = null;

			if (pName.contains(".")) {
				String[] pNameParts = pName.split("\\.");

				if (metadata.containsProperty(pNameParts[0])) {
					property = metadata.getProperty(pNameParts[0]);
					value = unmarshallNestedType(row, property.getMetadata(), property);
				}
			} else if (metadata.containsProperty(pName)){
				property = metadata.getProperty(pName);
				value = row.get(pName);
			}

			property.setValue(type, value);
		}

		return type;
	}

	private static <TYPE> TYPE unmarshallNestedType(Map<String, Object> row, Metadata<TYPE> metadata, MetaProperty property) {
		TYPE type = metadata.create();

		for (String pName : row.keySet()) {
			if (pName.contains(".")) {
				String[] pNameParts = pName.split("\\.");

				MetaProperty nProperty;
				Object value;

				if (pNameParts[0].equals(property.getName())) {
					nProperty = metadata.getProperty(pNameParts[1]);
					value = row.get(pName);

					nProperty.setValue(type, value);
				}


			}
		}

		return type;
	}
}
