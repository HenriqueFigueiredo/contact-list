package com.henrique.contactlist.mapper;

import com.henrique.contactlist.dto.ContactDTO;
import com.henrique.contactlist.dto.NewContactDTO;
import com.henrique.contactlist.dto.UpdateContactDTO;
import com.henrique.contactlist.model.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    Contact toContact(NewContactDTO dto);
    Contact toContact(UpdateContactDTO dto);
    ContactDTO toContactDTO(Contact contact);
}
