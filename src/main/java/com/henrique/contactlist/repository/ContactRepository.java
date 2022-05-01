package com.henrique.contactlist.repository;

import com.henrique.contactlist.repository.model.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class ContactRepository implements IContactRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public Contact create(final Contact contact) {
        this.getSession().save(contact);
        return contact;
    }

    @Transactional
    public List<Contact> listAll() {
        return this.getSession().createQuery("from Contact").list();
    }

    @Transactional
    public Optional<Contact> find(final String uuid) {
        final TypedQuery<Contact> query = this.getSession().createQuery("from Contact where uuid = :uuid", Contact.class)
                .setParameter("uuid", uuid);
        try {
            return Optional.of(query.getSingleResult());
        } catch (final NoResultException ex) {
            return Optional.empty();
        }
    }

    @Transactional
    public void update(final Contact contact) {
        this.getSession().update(contact);
    }

    @Transactional
    public void delete(final String uuid) {
        final Optional<Contact> contato = this.find(uuid);
        contato.ifPresent(ct -> this.getSession().delete(ct));
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}