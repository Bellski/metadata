package ru.bellski.metadata.unmarshaller;

import ru.bellski.metadata.MetaProperty;
import ru.bellski.metadata.Metadata;

import java.util.Map;

/**
 * Created by oem on 4/26/16.
 */
public class SQLUnmarshaler {
	public static <TYPE> TYPE unmarshall(Map<String, Object> data, Metadata<TYPE> metadata) {
		final TYPE type = metadata.createTYPE();

		data
			.keySet()
			.stream()
			.filter(metadata::isPropertyExists)
			.forEach(pName -> {
				final MetaProperty property = metadata.getProperty(pName);
				Object value = data.get(pName);

				if (metadata.isNestedProperty(property)) {
					value = unmarshall(data, metadata.getMetadataByProperty(property));
				} else if (property.getType().getSuperclass() == Enum.class) {
					value = Enum.valueOf(property.getType(), value.toString());
				}

				property.setValue(type, value);
			});

		return type;
	}
}
