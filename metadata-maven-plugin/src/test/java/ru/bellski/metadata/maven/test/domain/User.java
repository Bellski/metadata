package ru.bellski.metadata.maven.test.domain;

import ru.bellski.metadata.annotaion.GenerateMetadata;

/**
 * Created by oem on 4/26/16.
 */
@GenerateMetadata
public class User extends Person {
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
