package ru.bellski.metadata.examples.domain;

/**
 * Created by oem on 5/11/16.
 */
public class Address {
	private String country;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address{" +
			"country='" + country + '\'' +
			'}';
	}
}
