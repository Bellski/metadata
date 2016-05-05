package ru.bellski.metadata;

import org.junit.Assert;
import org.junit.Test;
import ru.bellski.metadata.domain.User;

import ru.bellski.metadata.unmarshaller.SQLUnmarshaler;
import ru.bellski.metadata.yet.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by oem on 4/26/16.
 */
public class MetadataTests {


	@Test
	public void propertiesTest() throws Exception {
		UserMetadata
			.user
			.getProperties()
			.forEach(metaProperty ->
				Assert.assertNotNull(UserMetadata.user.getProperty(metaProperty.getName()))
			);
	}

	@Test
	public void flatUnmarshalTest() throws Exception {
//		final HashMap<String, Object> data = new HashMap<>();
//		data.put(UserMetadata.user.name.getName(), "Foo");
//		data.put(AddressMetadata.address.country.getName(), "Russia");
//		data.put(AddressMetadata.address.street.getName(), "Cool street");
//		data.put(AddressMetadata.address.apartments.getName(), "1");
//
//		final User user = SQLUnmarshaler.unmarshall(data, UserMetadata.user);
//
//		Assert.assertTrue(user.getName().equals("Foo"));
//		Assert.assertTrue(user.getAddress() != null);
//		Assert.assertTrue(user.getAddress().getCountry().equals("Russia"));
//		Assert.assertTrue(user.getAddress().getStreet().equals("Cool street"));
//		Assert.assertTrue(user.getAddress().getApartments().equals("1"));
	}

	@Test
	public void newTest() throws Exception {
		final HashMap<String, Object> data = new HashMap<>();
		data.put(ru.bellski.metadata.UserMetadata.user.name().getName(), "Foo");
		data.put(ru.bellski.metadata.UserMetadata.user.address().nested().country(), "Russia");
		data.put(ru.bellski.metadata.UserMetadata.user.address().nested().street(), "Cool street");
		data.put(ru.bellski.metadata.UserMetadata.user.address().nested().apartments(), "1");
		data.put(ru.bellski.metadata.UserMetadata.user.address().nested().permanentAddress(), "Russia");
		data.put(ru.bellski.metadata.UserMetadata.user.address().nested().street(), "Cool street");
		data.put(ru.bellski.metadata.UserMetadata.user.address().nested().apartments(), "1");




		User un = SQLUnmarshaller.unmarshall(data, ru.bellski.metadata.UserMetadata.user);

		System.out.println(un);

	}

	@Test
	public void testBuilder() throws Exception {
		final HashMap<String, Object> data = new HashMap<>();
		data.put("name", "Foo");
		data.put("address.country", "Russia");
		data.put("address.street", "Cool street");
		data.put("address.apartments", "1");
		data.put("address.permanentAddress.country", "Russia");
		data.put("address.permanentAddress.street", "Cool street");
		data.put("address.permanentAddress.apartments", "1");

		System.out.println(convertSQLToJSON(data));
	}

	public Map<String, Object> convertSQLToJSON(HashMap<String, Object> data) {
		Map<String, Object> json = new HashMap<>();

		for (String pName : data.keySet()) {
			if (pName.contains(".")) {
				String[] pNameParts = pName.split("\\.");

				Map<String, Object> nestedValue = (Map) json.get(pNameParts[0]);

				if (nestedValue == null) {
					nestedValue = new HashMap<>();
					json.put(pNameParts[0], nestedValue);

					for (int i = 1; i < pNameParts.length; i++) {

					}
				}

			} else {
				json.put(pName, data.get(pName));
			}
		}

		return json;
	}
}
