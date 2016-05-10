package ru.bellski.metadata.maven.test.domain2;

import ru.bellski.metadata.MetaProperty;
import ru.bellski.metadata.Metadata;
import ru.bellski.metadata.maven.test.domain2.Address;
import java.lang.String;
import ru.bellski.metadata.maven.test.domain2.PermanentAddress;
import ru.bellski.metadata.maven.test.domain2.PermanentAddressMetadata;
import ru.bellski.metadata.maven.test.domain2.AddressMetadataProperties;
import ru.bellski.metadata.AbstractMetadata;

public class AddressMetadata extends AbstractMetadata<Address>
		implements
			AddressMetadataProperties<MetaProperty> {

	public static final AddressMetadata address = new AddressMetadata();
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
		public Metadata<String> getMetadata() {
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
		public Metadata<String> getMetadata() {
			return null;
		}
	};
	private final MetaProperty<Address, PermanentAddress> permanentAddress = new MetaProperty<Address, PermanentAddress>() {
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
		public Metadata<PermanentAddress> getMetadata() {
			return PermanentAddressMetadata.permanentAddress;
		}
	};
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
		public Metadata<String> getMetadata() {
			return null;
		}
	};

	@Override
	public MetaProperty<Address, String> street() {
		return street;
	}

	@Override
	public MetaProperty<Address, String> apartments() {
		return apartments;
	}

	@Override
	public MetaProperty<Address, PermanentAddress> permanentAddress() {
		return permanentAddress;
	}

	@Override
	public MetaProperty<Address, String> country() {
		return country;
	}

	@Override
	public Address create() {
		return new Address();
	}

	@Override
	public Class<Address> getType() {
		return Address.class;
	}
}