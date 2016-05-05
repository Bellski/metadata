package ru.bellski.metadata;

import ru.bellski.metadata.domain.Address;
import ru.bellski.metadata.domain.User;
import ru.bellski.metadata.yet.*;
import ru.bellski.metadata.yet.MetaProperty;
import ru.bellski.metadata.yet.Metadata;
import ru.bellski.metadata.yet.NestedMetaProperty;

import java.util.Collection;

/**
 * Created by oem on 5/4/16.
 */
public class UserMetadata extends AbstractMetadata<User> implements UserProperties<MetaProperty>{
	public static final UserMetadata user = new UserMetadata();

	private static class NestedAddressProperties implements AddressProperties<String> {
		public static NestedAddressProperties nestedAddress = new NestedAddressProperties();

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

		@Override
		public String permanentAddress() {
			return "address.permanentAddress";
		}
	}

	private final MetaProperty<User, String> name = new MetaProperty<User, String>() {
		@Override
		public String getName() {
			return "name";
		}

		@Override
		public Class<String> getType() {
			return String.class;
		}

		@Override
		public void setValue(User user, String value) {
			user.setName(value);
		}

		@Override
		public String getValue(User user) {
			return user.getName();
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

	ru.bellski.metadata.yet.NestedMetaProperty<User, Address, AddressProperties<String>> address = new NestedMetaProperty<User, Address, AddressProperties<String>>() {
		@Override
		public AddressProperties<String> nested() {
			return NestedAddressProperties.nestedAddress;
		}

		@Override
		public String getName() {
			return "address";
		}

		@Override
		public Class<Address> getType() {
			return Address.class;
		}

		@Override
		public void setValue(User user, Address value) {
			user.setAddress(value);
		}

		@Override
		public Address getValue(User user) {
			return user.getAddress();
		}

		@Override
		public boolean isNested() {
			return true;
		}

		@Override
		public Metadata getMetadata() {
			return AddressMetadata.address;
		}
	};

	{
		addProperties(name, address);
	}


	@Override
	public MetaProperty<User, String> name() {
		return name;
	}

	@Override
	public ru.bellski.metadata.yet.NestedMetaProperty<User, Address, AddressProperties<String>> address() {
		return address;
	}

	@Override
	public User create() {
		return new User();
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
