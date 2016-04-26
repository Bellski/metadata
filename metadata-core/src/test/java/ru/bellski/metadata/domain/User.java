package ru.bellski.metadata.domain;

import ru.bellski.metadata.annotaion.GenerateMetada;

/**
 * Created by oem on 4/26/16.
 */
@GenerateMetada
public class User {
	private String name;
	private Address address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User{" +
			"name='" + name + '\'' +
			", address=" + address +
			'}';
	}
}
