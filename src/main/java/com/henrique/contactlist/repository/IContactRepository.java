package com.henrique.contactlist.repository;

import com.henrique.contactlist.model.Contact;

import java.util.List;
import java.util.Optional;

public interface IContactRepository {
    Contact create(final Contact contact);
    List<Contact> listAll();
    Optional<Contact> find(final String uuid);
    void update(final Contact contact);
    void delete(final String uuid);
}
