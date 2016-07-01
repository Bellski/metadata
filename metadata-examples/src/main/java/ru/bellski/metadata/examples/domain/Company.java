package ru.bellski.metadata.examples.domain;

import java.util.Map;

/**
 * Информация о компании
 */
public class Company {

    /**
     * Номер компании в CAS2
     **/
    public Long id;

    /**
     * Имя компании
     **/
    public String name;

    public Company() {
        // dummy
    }

    public Company(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Company company = (Company) o;

        if (id != null ? !id.equals(company.id) : company.id != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }
}