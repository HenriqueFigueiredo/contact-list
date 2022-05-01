package com.henrique.contactlist.service;

import com.henrique.contactlist.controller.dto.ContactDTO;
import com.henrique.contactlist.controller.dto.NewContactDTO;
import com.henrique.contactlist.controller.dto.UpdateContactDTO;
import com.henrique.contactlist.repository.IContactRepository;
import com.henrique.contactlist.repository.model.Contact;
import com.henrique.contactlist.service.mapper.ContactMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ContactService test.")
class ContactServiceTest {

    @InjectMocks
    private ContactService contactService;

    @Mock
    private IContactRepository contactRepository;

    @Spy
    private ContactMapper contactMapper;

    @Test
    void shouldBePossibleToCreateANewContact() {
        //given a new contact
        final NewContactDTO newContactDTO = new NewContactDTO("henrique", "99995555", "henrique@email.com");

        //and all mappers and repository are running
        final Contact contactStub = new Contact();
        when(contactMapper.toContact(Mockito.any())).thenReturn(contactStub);
        when(contactRepository.create(contactStub)).thenReturn(contactStub);
        final ContactDTO contactDTOStub = new ContactDTO();
        when(contactMapper.toContactDTO(contactStub)).thenReturn(contactDTOStub);

        //when the service is called
        final ContactDTO result = this.contactService.create(newContactDTO);

        //then the contact is created using the repository
        assertEquals(contactDTOStub, result);
        verify(contactRepository, times(1)).create(contactStub);
    }

    @Test
    void shouldBePossibleListAllContacts() {
        //given that exists contacts
        final Contact existentContact1 = new Contact();
        final Contact existentContact2 = new Contact();
        final List<Contact> exstentContacts = Arrays.asList(existentContact1, existentContact2);

        //and all mappers and repository are running
        when(contactRepository.listAll()).thenReturn(exstentContacts);
        final ContactDTO contactDTOStub1 = new ContactDTO();
        final ContactDTO contactDTOStub2 = new ContactDTO();
        when(contactMapper.toContactDTO(existentContact1)).thenReturn(contactDTOStub1);
        when(contactMapper.toContactDTO(existentContact2)).thenReturn(contactDTOStub2);

        //when the service is called
        final List<ContactDTO> result = this.contactService.listAll();

        //then all the contacts are listed using the repository
        assertEquals(2, result.size());
        assertTrue(result.contains(contactDTOStub1));
        assertTrue(result.contains(contactDTOStub2));
        verify(contactRepository, times(1)).listAll();
    }

    @Test
    void shouldBePossibleUpdateAContact() {
        //given a update process
        final String uuid = UUID.randomUUID().toString();
        final UpdateContactDTO updateDTO = new UpdateContactDTO("henrique", "(00)11112222", "Henrique@email.com");

        //and the repository is running
        Contact contactMock = Mockito.mock(Contact.class);
        when(contactRepository.find(uuid)).thenReturn(Optional.of(contactMock));

        //when the service is called
        this.contactService.update(uuid, updateDTO);

        //then the contact is updated
        verify(contactRepository, times(1)).find(uuid);
        verify(contactRepository, times(1)).update(contactMock);
        verify(contactMock, times(1)).setName("Henrique");
        verify(contactMock, times(1)).setPhone("0011112222");
        verify(contactMock, times(1)).setEmail("henrique@email.com");
    }

    @Test
    void shouldNotBePossibleToUpdateANonExistentContact() {
        //given a update process
        final String uuid = UUID.randomUUID().toString();
        final UpdateContactDTO updateDTO = new UpdateContactDTO("henrique", "(00)11112222", "Henrique@email.com");

        //and the repository is running
        when(contactRepository.find(uuid)).thenReturn(Optional.empty());

        //when the service is called
        this.contactService.update(uuid, updateDTO);

        //then the update is not called
        verify(contactRepository, times(1)).find(uuid);
        verify(contactRepository, times(0)).update(Mockito.any());
    }

    @Test
    void shouldBePossibleUpdateTheNamFromAContact() {
        //given a update process
        final String uuid = UUID.randomUUID().toString();
        final String newName = "Bruce";

        //and the repository is running
        Contact contactMock = Mockito.mock(Contact.class);
        when(contactRepository.find(uuid)).thenReturn(Optional.of(contactMock));

        //when the service is called
        this.contactService.updateName(uuid, newName);

        //then the contact is updated
        verify(contactRepository, times(1)).find(uuid);
        verify(contactRepository, times(1)).update(contactMock);
        verify(contactMock, times(1)).setName("Bruce");
    }

    @Test
    void shouldNotBePossibleToUpdateTheNameForANotExistentContact() {
        //given a update process
        final String uuid = UUID.randomUUID().toString();
        final String newName = "Bruce";

        //and the repository is running
        when(contactRepository.find(uuid)).thenReturn(Optional.empty());

        //when the service is called
        this.contactService.updateName(uuid, newName);

        //then the update process is not called
        verify(contactRepository, times(1)).find(uuid);
        verify(contactRepository, times(0)).update(Mockito.any());
    }

    @Test
    void shouldBePossibleToDeleteAContac() {
        //given a delete process
        final String uuid = UUID.randomUUID().toString();

        //when the service is called
        this.contactService.delete(uuid);

        //then the contact is deleted
        verify(contactRepository, times(1)).delete(uuid);
    }
}