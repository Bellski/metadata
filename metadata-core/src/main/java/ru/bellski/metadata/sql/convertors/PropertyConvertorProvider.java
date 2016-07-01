package ru.bellski.metadata.sql.convertors;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by oem on 7/1/16.
 */
public class PropertyConvertorProvider {
	private Map<Class<?>, PropertyConverter<?,?>> converterMap = new HashMap<>();

	{
		converterMap.put(XMLGregorianCalendar.class, new XMLGregorianCalendarConverter());
	}

	public static final PropertyConvertorProvider get = new PropertyConvertorProvider();

	public <FROM, TO> PropertyConverter<FROM, TO> getConvertor(Class<TO> to) {
		return (PropertyConverter<FROM, TO>) converterMap.get(to);
	}

	public boolean contains(Class<?> to) {
		return converterMap.containsKey(to);
	}
}
