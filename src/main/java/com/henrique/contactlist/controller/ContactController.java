package com.henrique.contactlist.controller;

import com.henrique.contactlist.controller.dto.ContactDTO;
import com.henrique.contactlist.controller.dto.NewContactDTO;
import com.henrique.contactlist.controller.dto.UpdateContactDTO;
import com.henrique.contactlist.service.IContactService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {

    Logger logger = LoggerFactory.getLogger(ContactController.class);

    final private IContactService contactService;

    public ContactController(final IContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping(path = "/api/v1/contacts")
    @Timed(value = "contacts.create.time", description = "Time taken to create a contact")
    public ResponseEntity<ContactDTO> create(@RequestBody NewContactDTO newContactDTO) {
        logger.info(String.format("Creating new contact (%s)", newContactDTO.getName()));
        final ContactDTO result = this.contactService.create(newContactDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/contacts")
    @Timed(value = "contacts.list.time", description = "Time taken to list all contacts")
    @Counted(value = "contacts.list.count")
    public List<ContactDTO> listAll() {
        logger.info("Performing contacts list.");
        return this.contactService.listAll();
    }

    @PutMapping(path = "/api/v1/contacts/{id}")
    @Timed(value = "contacts.update.time", description = "Time taken to update a contact")
    public ResponseEntity update(@PathVariable(value = "id") final String uuid,
                                 @RequestBody final UpdateContactDTO contactDTO) {
        logger.info(String.format("Updating the contact (%s)", uuid));
        this.contactService.update(uuid, contactDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(path = "/api/v1/contacts/{id}/name/{name}")
    @Timed(value = "contacts.update.name.time", description = "Time taken to update the name of a contact")
    public ResponseEntity updateNome(@PathVariable(value = "id") final String uuid,
                                     @PathVariable(value = "name") final String name) {
        logger.info(String.format("Updating contact name (%s) for contact (%s)", name, uuid));
        this.contactService.updateName(uuid, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/contacts/{id}")
    @Timed(value = "contacts.delete.time", description = "Time taken to delete a contact")
    public ResponseEntity delete(@PathVariable(value = "id") final String uuid) {
        logger.info(String.format("Deleting the contact (%s)", uuid));
        this.contactService.delete(uuid);
        return new ResponseEntity(HttpStatus.OK);
    }
}
