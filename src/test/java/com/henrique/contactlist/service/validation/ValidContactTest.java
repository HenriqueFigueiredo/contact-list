package com.henrique.contactlist.service.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("ValidContact test.")
class ValidContactTest {

    @Test
    void shouldBePossibleToCreateAValidContact() {
        final String name = "Henrique Silva Figueiredo";
        final String phone = "5500999991111";
        final String email = "henrique@email.com";
        final ValidContact validContact = new ValidContact(name, phone, email);
        assertEquals("Henrique Silva Figueiredo", validContact.getName());
        assertEquals("5500999991111", validContact.getPhone());
        assertEquals("henrique@email.com", validContact.getEmail());
        assertNotNull(validContact.getUuid());
    }

    @Test
    void shouldHandleFormInformationsFromAValidContact() {
        final String name = "henrique Silva figueiredo";
        final String phone = "+55(00)99999-1111";
        final String email = "HENRIQUE@email.com      ";
        final ValidContact validContact = new ValidContact(name, phone, email);
        assertEquals("Henrique Silva Figueiredo", validContact.getName());
        assertEquals("5500999991111", validContact.getPhone());
        assertEquals("henrique@email.com", validContact.getEmail());
    }
}