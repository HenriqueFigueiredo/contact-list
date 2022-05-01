package com.henrique.contactlist.service;

import com.henrique.contactlist.controller.dto.ContactDTO;
import com.henrique.contactlist.controller.dto.NewContactDTO;
import com.henrique.contactlist.controller.dto.UpdateContactDTO;

import java.util.List;

public interface IContactService {
    ContactDTO create(final NewContactDTO newContactDTO);
    List<ContactDTO> listAll();
    void update(final String uuid, final UpdateContactDTO updateContato);
    void updateName(final String uuid, final String nome);
    void delete(final String uuid);
}
