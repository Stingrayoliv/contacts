package de.telran.contacts.repository;

import de.telran.contacts.model.Contact;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryContactRepoTest {

    @Test
    void save() {
        InMemoryContactRepo inMemoryContactRepo = new InMemoryContactRepo();
        Contact contact = new Contact(0, "Name1", "LastName1", 3);
        Contact contact1 = new Contact(1, "Name1Change", "LastName1", 3);
        // contact added the first time - id will be changed
        inMemoryContactRepo.save(contact);
        // the same contact added the second time / edit/ id is the same
        inMemoryContactRepo.save(contact1);

        assertEquals(new Contact(1, "Name1Change", "LastName1", 3),
                inMemoryContactRepo.find(1));
    }

    @Test
    void exceptionTesting() {
        InMemoryContactRepo inMemoryContactRepo = new InMemoryContactRepo();
        // any contact in map
        // add the first time a contact, with incorrect id=100
        Contact contact1 = new Contact(100, "Name1Change", "LastName1", 3);
        Throwable exception = assertThrows(ContactNotFoundException.class, () -> {
            throw new ContactNotFoundException("Incorrect id : " + contact1.getId());
        });
        assertEquals("Incorrect id : " + contact1.getId(), exception.getMessage());
    }

    @Test
    void existingId_existingContact_find() {
        InMemoryContactRepo inMemoryContactRepo = new InMemoryContactRepo();
        inMemoryContactRepo.save(new Contact(0, "Name6", "LastName6", 6));
        inMemoryContactRepo.save(new Contact(0, "Name4", "LastName4", 4));
        inMemoryContactRepo.save(new Contact(1, "Name6", "LastName6", 6));
        assertEquals(new Contact(2, "Name4", "LastName4", 4), inMemoryContactRepo.find(2));
    }

    @Test
    void IdIsNotExistent_null_find() {
        InMemoryContactRepo inMemoryContactRepo = new InMemoryContactRepo();
        inMemoryContactRepo.save(new Contact(0, "Name5", "LastName5", 5));
        inMemoryContactRepo.save(new Contact(1, "Name5", "LastName5", 5));
        assertNull(inMemoryContactRepo.find(2));
    }


    @Test
    void existingId_existingContact_remove() {
        InMemoryContactRepo inMemoryContactRepo = new InMemoryContactRepo();
        inMemoryContactRepo.save(new Contact(0, "Name6", "LastName6", 6));
        inMemoryContactRepo.save(new Contact(0, "Name4", "LastName4", 4));
        inMemoryContactRepo.save(new Contact(1, "Name6", "LastName6", 6));
        assertEquals(new Contact(2, "Name4", "LastName4", 4), inMemoryContactRepo.remove(2));
    }

    @Test
    void IdIsNotExistent_null_remove() {
        InMemoryContactRepo inMemoryContactRepo = new InMemoryContactRepo();
        inMemoryContactRepo.save(new Contact(0, "Name5", "LastName5", 5));
        inMemoryContactRepo.save(new Contact(1, "Name5", "LastName5", 5));
        assertNull(inMemoryContactRepo.remove(2));
    }

    @Test
    void findAll() {
        InMemoryContactRepo inMemoryContactRepo = new InMemoryContactRepo();
        List<Contact> list = new ArrayList<>();
        list = Arrays.asList(new Contact(1, "Name1", "LastName1", 1),
                new Contact(2, "Name2", "LastName2", 2));

        inMemoryContactRepo.save(new Contact(0, "Name1", "LastName1", 1));
        inMemoryContactRepo.save(new Contact(0, "Name2", "LastName2", 2));
        assertEquals(list, inMemoryContactRepo.findAll());
    }
}
