package ru.bellski.metadata.maven.test.domain2;


public class Address {
    private String country;
    private String street;
    private String apartments;
    private PermanentAddress permanentAddress;

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

    public PermanentAddress getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(PermanentAddress permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

}
