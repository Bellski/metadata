package ru.bellski.metadata.domain.metadata;

import ru.bellski.metadata.MetaProperty;
import ru.bellski.metadata.Metadata;
import ru.bellski.metadata.NestedMetaProperty;
import ru.bellski.metadata.domain.Address;
import ru.bellski.metadata.domain.User;
import ru.bellski.metadata.impl.AbstractMetadata;

/**
 * Created by oem on 4/26/16.
 */
public class UserMetadata extends AbstractMetadata<User> {
	public static final UserMetadata user = new UserMetadata();

	public final MetaProperty<User, String> name = new MetaProperty<User, String>() {
		@Override
		public String getName() {
			return "User.hierarchicalUnmarshalTest";
		}

		@Override
		public String getSimpleName() {
			return "hierarchicalUnmarshalTest";
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
	};

	public final MetaProperty<User, Address> address = new NestedMetaProperty<User, Address>() {

		@Override
		public String getName() {
			return "User.address";
		}

		@Override
		public String getSimpleName() {
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
		public Address create() {
			return new Address();
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
	public User createTYPE() {
		return new User();
	}
}
