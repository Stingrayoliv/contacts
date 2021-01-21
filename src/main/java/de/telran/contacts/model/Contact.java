package de.telran.contacts.model;

import java.util.Objects;

public class Contact {
    private int id;
    private String name;
    private String lastName;
    private int age;

    public Contact(int id, String name, String lastName, int age) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Contact() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id &&
                age == contact.age &&
                Objects.equals(name, contact.name) &&
                Objects.equals(lastName, contact.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, age);
    }
}
