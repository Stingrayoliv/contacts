package de.telran.contacts.repository;

import de.telran.contacts.model.Contact;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class InMemoryContactRepo implements IContactRepo {
    private Map<Integer, Contact> source;

    public InMemoryContactRepo(Map<Integer, Contact> source) {
        this.source = source;
    }

    @Override
    public void save(Contact contact) {
        source = new HashMap<>();
        List<Integer> sortedId=new ArrayList<>();
        sortedId = source.keySet().stream().sorted().collect(Collectors.toList());
        int lastId = sortedId.get(sortedId.size()-1);

        if (contact.getId() == 0) {
            contact.setId(lastId + 1);
            source.put(lastId + 1, contact);
        } else {
            source.put(contact.getId(), contact);
        }
    }

    @Override
    public Contact find(int id) {
        if (!source.containsKey(id)) {
            return null;
        } else {
            return source.get(id);
        }
    }

    @Override
    public Contact remove(int id) {
        if (!source.containsKey(id)) {
            return null;
        } else {
            return remove(id);
        }
    }

    @Override
    public List<Contact> findAll() {
        List<Contact> list = new ArrayList<>(source.values());
        return list;
    }
}
