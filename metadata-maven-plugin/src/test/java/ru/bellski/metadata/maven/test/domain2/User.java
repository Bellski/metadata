package ru.bellski.metadata.maven.test.domain2;


import javax.xml.datatype.XMLGregorianCalendar;

public class User {
    private String nameName;
    private Address home;
    private Address delivery;
    private XMLGregorianCalendar date;

    public String getNameName() {
        return nameName;
    }

    public void setNameName(String nameName) {
        this.nameName = nameName;
    }

    public Address getHome() {
        return home;
    }

    public void setHome(Address home) {
        this.home = home;
    }

    public Address getDelivery() {
        return delivery;
    }

    public void setDelivery(Address delivery) {
        this.delivery = delivery;
    }

    public XMLGregorianCalendar getDate() {
        return date;
    }

    public void setDate(XMLGregorianCalendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
            "nameName='" + nameName + '\'' +
            ", home=" + home +
            ", delivery=" + delivery +
            '}';
    }

}
