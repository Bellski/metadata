package ru.bellski.metadata.domain.metadata;

import ru.bellski.metadata.MetaProperty;
import ru.bellski.metadata.domain.Address;
import ru.bellski.metadata.impl.AbstractMetadata;

/**
 * Created by oem on 4/26/16.
 */
public class AddressMetadata extends AbstractMetadata<Address> {
	public static final AddressMetadata address = new AddressMetadata();

	public final MetaProperty<Address, String> country = new MetaProperty<Address, String>() {
		@Override
		public String getName() {
			return "Address.country";
		}

		@Override
		public String getSimpleName() {
			return "country";
		}

		@Override
		public Class<String> getType() {
			return String.class;
		}

		@Override
		public void setValue(Address address, String value) {
			address.setCountry(value);
		}

		@Override
		public String getValue(Address address) {
			return address.getCountry();
		}
	};

	public final MetaProperty<Address, String> street = new MetaProperty<Address, String>() {
		@Override
		public String getName() {
			return "Address.street";
		}

		@Override
		public String getSimpleName() {
			return "street";
		}

		@Override
		public Class<String> getType() {
			return String.class;
		}

		@Override
		public void setValue(Address address, String value) {
			address.setStreet(value);
		}

		@Override
		public String getValue(Address address) {
			return address.getStreet();
		}
	};

	public final MetaProperty<Address, String> apartments = new MetaProperty<Address, String>() {
		@Override
		public String getName() {
			return "Address.apartments";
		}

		@Override
		public String getSimpleName() {
			return "apartments";
		}

		@Override
		public Class<String> getType() {
			return String.class;
		}

		@Override
		public void setValue(Address address, String value) {
			address.setApartments(value);
		}

		@Override
		public String getValue(Address address) {
			return address.getApartments();
		}
	};

	{
		addProperties(country, street, apartments);
	}

	@Override
	public Address createTYPE() {
		return new Address();
	}
}
