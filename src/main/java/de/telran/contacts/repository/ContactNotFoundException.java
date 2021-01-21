package de.telran.contacts.repository;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
