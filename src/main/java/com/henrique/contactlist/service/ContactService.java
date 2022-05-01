package com.henrique.contactlist.service;

import com.henrique.contactlist.controller.dto.ContactDTO;
import com.henrique.contactlist.controller.dto.NewContactDTO;
import com.henrique.contactlist.controller.dto.UpdateContactDTO;
import com.henrique.contactlist.repository.IContactRepository;
import com.henrique.contactlist.repository.model.Contact;
import com.henrique.contactlist.service.mapper.ContactMapper;
import com.henrique.contactlist.service.validation.ValidContact;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class ContactService implements IContactService {

    final private IContactRepository contactRepository;
    final private ContactMapper contactMapper;

    public ContactService(final IContactRepository contactRepository, final ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    public ContactDTO create(final NewContactDTO dto) {
        final ValidContact validContact = new ValidContact(dto.getName(), dto.getPhone(), dto.getEmail());
        final Contact contact = this.contactMapper.toContact(validContact);
        return this.contactMapper.toContactDTO(this.contactRepository.create(contact));
    }

    public List<ContactDTO> listAll() {
        return this.contactRepository.listAll().stream().map(ct -> this.contactMapper.toContactDTO(ct)).collect(toList());
    }

    @Transactional
    public void update(final String uuid, final UpdateContactDTO dto) {
        final ValidContact validContact = new ValidContact(dto.getName(), dto.getPhone(), dto.getEmail());
        final Optional<Contact> contact = this.contactRepository.find(uuid);
        contact.ifPresent(ct -> {
            ct.setName(validContact.getName());
            ct.setPhone(validContact.getPhone());
            ct.setEmail(validContact.getEmail());
            this.contactRepository.update(ct);
        });
    }

    @Transactional
    public void updateName(final String uuid, final String name) {
        this.contactRepository.find(uuid).ifPresent(ct -> {
            ct.setName(name);
            this.contactRepository.update(ct);
        });
    }

    public void delete(final String uuid) {
        this.contactRepository.delete(uuid);
    }

}
