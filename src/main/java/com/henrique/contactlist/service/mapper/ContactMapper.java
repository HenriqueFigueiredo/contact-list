package com.henrique.contactlist.service.mapper;

import com.henrique.contactlist.controller.dto.ContactDTO;
import com.henrique.contactlist.controller.dto.NewContactDTO;
import com.henrique.contactlist.controller.dto.UpdateContactDTO;
import com.henrique.contactlist.repository.model.Contact;
import com.henrique.contactlist.service.validation.ValidContact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    Contact toContact(ValidContact validContact);
    ContactDTO toContactDTO(Contact contact);
}
