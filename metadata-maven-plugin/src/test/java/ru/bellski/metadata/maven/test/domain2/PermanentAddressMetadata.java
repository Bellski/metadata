package ru.bellski.metadata.maven.test.domain2;

import ru.bellski.metadata.MetaProperty;
import ru.bellski.metadata.Metadata;
import ru.bellski.metadata.maven.test.domain2.PermanentAddress;
import java.lang.String;
import ru.bellski.metadata.maven.test.domain2.PermanentAddressMetadataProperties;
import ru.bellski.metadata.AbstractMetadata;

public class PermanentAddressMetadata
		extends
			AbstractMetadata<PermanentAddress>
		implements
			PermanentAddressMetadataProperties<MetaProperty> {

	public static final PermanentAddressMetadata permanentAddress = new PermanentAddressMetadata();
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
		public void setValue(PermanentAddress permanentAddress, String value) {
			permanentAddress.setApartments(value);
		}

		@Override
		public String getValue(PermanentAddress permanentAddress) {
			return permanentAddress.getApartments();
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
		public void setValue(PermanentAddress permanentAddress, String value) {
			permanentAddress.setStreet(value);
		}

		@Override
		public String getValue(PermanentAddress permanentAddress) {
			return permanentAddress.getStreet();
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
		public void setValue(PermanentAddress permanentAddress, String value) {
			permanentAddress.setCountry(value);
		}

		@Override
		public String getValue(PermanentAddress permanentAddress) {
			return permanentAddress.getCountry();
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
	public MetaProperty<PermanentAddress, String> apartments() {
		return apartments;
	}

	@Override
	public MetaProperty<PermanentAddress, String> street() {
		return street;
	}

	@Override
	public MetaProperty<PermanentAddress, String> country() {
		return country;
	}

	@Override
	public PermanentAddress create() {
		return new PermanentAddress();
	}

	@Override
	public Class<PermanentAddress> getType() {
		return PermanentAddress.class;
	}
}