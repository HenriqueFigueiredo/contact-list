package com.henrique.contactlist.service.validation.decorators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("NoSpaces test.")
class NoSpacesTest {

    @Test
    void shouldRemoveAllSpacesFromASingleWord() {
        final String singleWord = "  singleWord ";
        final String result = new NoSpaces(new BaseValue(singleWord)).getValue();
        assertEquals("singleWord", result);
    }

    @Test
    void shouldRemoveAllSpacesFromALArgeString() {
        final String largeString = "  large string ";
        final String result = new NoSpaces(new BaseValue(largeString)).getValue();
        assertEquals("largestring", result);
    }

    @Test
    void shouldRemoveAllSpacesFromABlankString() {
        final String blankString = "   ";
        final String result = new NoSpaces(new BaseValue(blankString)).getValue();
        assertEquals("", result);
    }
}