package ru.bellski.metadata.maven.test.domain;

import ru.bellski.metadata.annotaion.GenerateMetadata;

/**
 * Created by oem on 4/26/16.
 */
@GenerateMetadata
public class Address {
	private String country;
	private String street;
	private String apartments;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getApartments() {
		return apartments;
	}

	public void setApartments(String apartments) {
		this.apartments = apartments;
	}

	@Override
	public String toString() {
		return "Address{" +
			"country='" + country + '\'' +
			", street='" + street + '\'' +
			", apartments='" + apartments + '\'' +
			'}';
	}
}
