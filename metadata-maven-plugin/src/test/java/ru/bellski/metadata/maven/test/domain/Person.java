package ru.bellski.metadata.maven.test.domain;

/**
 * Created by oem on 4/28/16.
 */
public class Person {
	protected String name;
	private Person parentPerson;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person getParentPerson() {
		return parentPerson;
	}

	public void setParentPerson(Person parentPerson) {
		this.parentPerson = parentPerson;
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", parentPerson=" + parentPerson +
				'}';
	}
}
