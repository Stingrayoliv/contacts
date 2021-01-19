package de.telran.contacts.repository;

import de.telran.contacts.model.Contact;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryContactRepoTest {

    Contact contact1 = new Contact(1, "Lora1", "LastName1", 20);
    Contact contact2 = new Contact(2, "Lora2", "LastName2", 21);
    Contact contact3 = new Contact(3, "Lora3", "LastName3", 23);


    @Test
    void newContact_idEquals0_save() {
        Contact contact4 = new Contact(0, "Lora4", "LastName4", 23);

        Map<Integer, Contact> map = new HashMap<>();
        map.put(contact2.getId(), contact2);
        map.put(contact1.getId(), contact1);
        map.put(contact3.getId(), contact3);

        InMemoryContactRepo inMemoryContactRepo = new InMemoryContactRepo(map);
        inMemoryContactRepo.save(contact4);

        assertEquals(contact4, inMemoryContactRepo.find(4));
    }


    @Test
    void existingId_existingContact_find() {
        Contact contact4 = new Contact(0, "Lora3", "LastName3", 23);

        Map<Integer, Contact> map = new HashMap<>();
        map.put(contact1.getId(), contact1);
        map.put(contact2.getId(), contact2);
        map.put(contact3.getId(), contact3);

        InMemoryContactRepo inMemoryContactRepo = new InMemoryContactRepo(map);
        assertEquals(contact1, inMemoryContactRepo.find(1));
    }

    @Test
    void IdIsNotExistent_null_find() {
        Contact contact4 = new Contact(0, "Lora3", "LastName3", 23);

        Map<Integer, Contact> map = new HashMap<>();
        map.put(contact1.getId(), contact1);
        map.put(contact2.getId(), contact2);
        map.put(contact3.getId(), contact3);

        InMemoryContactRepo inMemoryContactRepo = new InMemoryContactRepo(map);
        assertEquals(null, inMemoryContactRepo.find(5));
    }

    @Test
    void existingId_existingContact_remove() {
        Contact contact4 = new Contact(0, "Lora3", "LastName3", 23);

        Map<Integer, Contact> map = new HashMap<>();
        map.put(contact1.getId(), contact1);
        map.put(contact2.getId(), contact2);
        map.put(contact3.getId(), contact3);

        InMemoryContactRepo inMemoryContactRepo = new InMemoryContactRepo(map);
        assertEquals(contact2, inMemoryContactRepo.find(2));
    }

    @Test
    void IdIsNotExistent_null_remove() {
        Contact contact4 = new Contact(0, "Lora3", "LastName3", 23);

        Map<Integer, Contact> map = new HashMap<>();
        map.put(contact1.getId(), contact1);
        map.put(contact2.getId(), contact2);
        map.put(contact3.getId(), contact3);

        InMemoryContactRepo inMemoryContactRepo = new InMemoryContactRepo(map);
        assertEquals(null, inMemoryContactRepo.find(5));
    }

    @Test
    void findAll() {
        Contact contact4 = new Contact(0, "Lora3", "LastName3", 23);

        Map<Integer, Contact> map = new HashMap<>();
        map.put(contact1.getId(), contact1);
        map.put(contact2.getId(), contact2);
        map.put(contact3.getId(), contact3);

        List<Contact> list = Arrays.asList(contact1, contact2, contact3);

        InMemoryContactRepo inMemoryContactRepo = new InMemoryContactRepo(map);
        assertEquals(list, inMemoryContactRepo.findAll());
    }
}