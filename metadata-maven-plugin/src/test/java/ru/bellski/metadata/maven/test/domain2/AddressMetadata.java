package ru.bellski.metadata.maven.test.domain2;

import ru.bellski.metadata.anewone.AbstractMetadata;
import ru.bellski.metadata.anewone.MetaProperty;
import ru.bellski.metadata.anewone.Metadata;
import ru.bellski.metadata.anewone.NestedMetaProperty;

import java.util.Collection;

/**
 * Created by oem on 5/4/16.
 */
public class AddressMetadata extends AbstractMetadata<Address> implements AddressProperties<MetaProperty> {
	public static final AddressMetadata address = new AddressMetadata();

	private static class NestedPermanentAddressProperties implements PermanentAddressProperties<String> {
		public static NestedPermanentAddressProperties nestedPermanentAddress = new NestedPermanentAddressProperties();

		@Override
		public String country() {
			return "address.country";
		}

		@Override
		public String street() {
			return "address.street";
		}

		@Override
		public String apartments() {
			return "address.apartments";
		}
	}

	private final MetaProperty<Address, String> country = new MetaProperty<Address, String>() {
		@Override
		public String getName() {
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

		@Override
		public boolean isNested() {
			return false;
		}

		@Override
		public Metadata getMetadata() {
			return null;
		}
	};

	private final MetaProperty<Address, String> street = new MetaProperty<Address, String>() {
		@Override
		public String getName() {
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

		@Override
		public boolean isNested() {
			return false;
		}

		@Override
		public Metadata getMetadata() {
			return null;
		}
	};

	private final MetaProperty<Address, String> apartments = new MetaProperty<Address, String>() {
		@Override
		public String getName() {
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

		@Override
		public boolean isNested() {
			return false;
		}

		@Override
		public Metadata getMetadata() {
			return null;
		}
	};

	NestedMetaProperty<Address, PermanentAddress, PermanentAddressProperties<String>> permanentAddress = new NestedMetaProperty<Address, PermanentAddress, PermanentAddressProperties<String>>() {

		@Override
		public String getName() {
			return "permanentAddress";
		}

		@Override
		public Class<PermanentAddress> getType() {
			return PermanentAddress.class;
		}

		@Override
		public void setValue(Address address, PermanentAddress value) {
			address.setPermanentAddress(value);
		}

		@Override
		public PermanentAddress getValue(Address address) {
			return address.getPermanentAddress();
		}

		@Override
		public boolean isNested() {
			return true;
		}

		@Override
		public Metadata getMetadata() {
			return PermanentAddressMetadata.permanentAddress;
		}

		@Override
		public PermanentAddressProperties<String> nested() {
			return NestedPermanentAddressProperties.nestedPermanentAddress;
		}
	};


	{
		addProperties(country, street, apartments, permanentAddress);
	}

	@Override
	public MetaProperty country() {
		return country;
	}

	@Override
	public MetaProperty street() {
		return street;
	}

	@Override
	public MetaProperty apartments() {
		return apartments;
	}

	@Override
	public MetaProperty permanentAddress() {
		return permanentAddress;
	}

	@Override
	public Address create() {
		return new Address();
	}

	@Override
	public Collection<MetaProperty> getProperties() {
		return propertyMap.values();
	}

	@Override
	public boolean containsProperty(String name) {
		return propertyMap.containsKey(name);
	}

	@Override
	public MetaProperty getProperty(String name) {
		return propertyMap.get(name);
	}
}
