package ru.bellski.metadata.unmarshaller;

import ru.bellski.metadata.MetaProperty;
import ru.bellski.metadata.Metadata;
import ru.bellski.metadata.NestedMetaProperty;

import java.util.*;

/**
 * Created by oem on 4/26/16.
 */
public class SQLUnmarshaler<TYPE> {
    private final Metadata<TYPE> metadata;

    private Map<Class<?>, Metadata> mapMeta = new HashMap<>();
    private Map<Object, TYPE> valueMap = new HashMap<>();

    public SQLUnmarshaler(Metadata<TYPE> metadata, Class<TYPE> type) {
        this.metadata = metadata;
    }

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

    public TYPE unmarshall(Map<String, Object> data) {
        final TYPE type = metadata.createTYPE();

        data
                .keySet()
                .stream()
                .filter(metadata::isPropertyExists)
                .forEach(pName -> {
                    String s = "";

                    final MetaProperty property = metadata.getProperty(pName);
                    Object value = data.get(pName);

                    if (metadata.isNestedProperty(property)) {
                        value = unmarshall(data, metadata.getMetadataByProperty(property));
                    } else if (property.getType().getSuperclass() == Enum.class) {
                        value = Enum.valueOf(property.getType(), value.toString());
                    }

                    valueMap.put(value, type);

                    property.setValue(type, value);

                });

        return type;
    }

    public static <TYPE> List<TYPE> unmarshallTree(List<Map<String, Object>> datas, Metadata<TYPE> metadata, MetaProperty<TYPE, ?> parentName) {
        final Map<Object, TYPE> valuesMap = new HashMap<>();

        final List<TYPE> types = new ArrayList<>();

        for (Map<String, Object> data : datas) {
            TYPE type = metadata.createTYPE();

            for (String pName : data.keySet()) {
                if (metadata.isPropertyExists(pName)) {
                    final MetaProperty property = metadata.getProperty(pName);
                    Object value = data.get(pName);

                    if (value != null) {
                        if (metadata.isNestedProperty(property)) {
                            NestedMetaProperty nProperty = (NestedMetaProperty) property;

                            if (nProperty.getMetadata().equals(metadata)) {
                                value = valuesMap.get(value);
                            } else {
                                value = unmarshall(data, nProperty.getMetadata());
                            }
                        }
                    }

                    if (property.equals(parentName)) {
                        valuesMap.put(value, type);
                    }

                    property.setValue(type, value);
                }
            }

            types.add(type);
        }

        return types;
    }
}
