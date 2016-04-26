package ru.bellski.metadata;

import org.junit.Assert;
import org.junit.Test;
import ru.bellski.metadata.domain.User;
import ru.bellski.metadata.domain.metadata.AddressMetadata;
import ru.bellski.metadata.domain.metadata.UserMetadata;
import ru.bellski.metadata.unmarshaller.SQLUnmarshaler;

import java.util.HashMap;

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
		final HashMap<String, Object> data = new HashMap<>();
		data.put(UserMetadata.user.name.getName(), "Foo");
		data.put(AddressMetadata.address.country.getName(), "Russia");
		data.put(AddressMetadata.address.street.getName(), "Cool street");
		data.put(AddressMetadata.address.apartments.getName(), "1");

		final User user = SQLUnmarshaler.unmarshall(data, UserMetadata.user);

		Assert.assertTrue(user.getName().equals("Foo"));
		Assert.assertTrue(user.getAddress() != null);
		Assert.assertTrue(user.getAddress().getCountry().equals("Russia"));
		Assert.assertTrue(user.getAddress().getStreet().equals("Cool street"));
		Assert.assertTrue(user.getAddress().getApartments().equals("1"));
	}

}
