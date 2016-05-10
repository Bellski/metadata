package ru.bellski.metadata.maven.test.domain2;

import ru.bellski.metadata.MetaProperty;
import ru.bellski.metadata.Metadata;
import ru.bellski.metadata.maven.test.domain2.User;
import ru.bellski.metadata.maven.test.domain2.Address;
import ru.bellski.metadata.maven.test.domain2.AddressMetadata;
import java.lang.String;
import ru.bellski.metadata.maven.test.domain2.UserMetadataProperties;
import ru.bellski.metadata.AbstractMetadata;

public class UserMetadata extends AbstractMetadata<User>
		implements
			UserMetadataProperties<MetaProperty> {

	public static final UserMetadata user = new UserMetadata();
	private final MetaProperty<User, Address> address = new MetaProperty<User, Address>() {
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
		public Metadata<Address> getMetadata() {
			return AddressMetadata.address;
		}
	};
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
		public Metadata<String> getMetadata() {
			return null;
		}
	};

	@Override
	public MetaProperty<User, Address> address() {
		return address;
	}

	@Override
	public MetaProperty<User, String> name() {
		return name;
	}

	@Override
	public User create() {
		return new User();
	}

	@Override
	public Class<User> getType() {
		return User.class;
	}
}