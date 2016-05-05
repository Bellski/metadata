package ru.bellski.metadata;

import ru.bellski.metadata.domain.PermanentAddress;
import ru.bellski.metadata.yet.AbstractMetadata;
import ru.bellski.metadata.yet.MetaProperty;
import ru.bellski.metadata.yet.Metadata;

import java.util.Collection;

/**
 * Created by oem on 5/5/16.
 */
public class PermanentAddressMetadata extends AbstractMetadata<PermanentAddress> implements PermanentAddressProperties<MetaProperty>{
	public static PermanentAddressMetadata permanentAddress = new PermanentAddressMetadata();

	private final MetaProperty<PermanentAddress, String> country = new MetaProperty<PermanentAddress, String>() {
		@Override
		public String getName() {
			return "country";
		}

		@Override
		public Class<String> getType() {
			return String.class;
		}

		@Override
		public void setValue(PermanentAddress address, String value) {
			address.setCountry(value);
		}

		@Override
		public String getValue(PermanentAddress address) {
			return address.getCountry();
		}

		@Override
		public boolean isNested() {
			return false;
		}

		@Override
		public ru.bellski.metadata.yet.Metadata getMetadata() {
			return null;
		}
	};

	private final MetaProperty<PermanentAddress, String> street = new MetaProperty<PermanentAddress, String>() {
		@Override
		public String getName() {
			return "street";
		}

		@Override
		public Class<String> getType() {
			return String.class;
		}

		@Override
		public void setValue(PermanentAddress address, String value) {
			address.setStreet(value);
		}

		@Override
		public String getValue(PermanentAddress address) {
			return address.getStreet();
		}

		@Override
		public boolean isNested() {
			return false;
		}

		@Override
		public ru.bellski.metadata.yet.Metadata getMetadata() {
			return null;
		}
	};

	private final MetaProperty<PermanentAddress, String> apartments = new MetaProperty<PermanentAddress, String>() {
		@Override
		public String getName() {
			return "apartments";
		}

		@Override
		public Class<String> getType() {
			return String.class;
		}

		@Override
		public void setValue(PermanentAddress address, String value) {
			address.setApartments(value);
		}

		@Override
		public String getValue(PermanentAddress address) {
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

	{
		addProperties(country, street, apartments);
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
	public PermanentAddress create() {
		return new PermanentAddress();
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
