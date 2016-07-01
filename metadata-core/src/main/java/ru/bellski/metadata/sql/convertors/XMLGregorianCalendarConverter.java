package ru.bellski.metadata.sql.convertors;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Date;
import java.util.GregorianCalendar;

/**
 * Created by oem on 7/1/16.
 */
public class XMLGregorianCalendarConverter implements PropertyConverter<Date, XMLGregorianCalendar> {
	@Override
	public XMLGregorianCalendar convert(Date date) {
		final GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);

		XMLGregorianCalendar xmlGregorianCalendar;
		try {
			xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}


		return xmlGregorianCalendar;
	}
}
