package com.henrique.contactlist.service.validation.decorators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("BaseValue test.")
class BaseValueTest {

    @Test
    void shouldHandleNullStringsAsEmpty() {
        final String nullString = null;
        final String result = new BaseValue(nullString).getValue();
        assertEquals("", result);
    }

    @Test
    void shouldKeepNotNullStrings() {
        final String nullString = " ";
        final String result = new BaseValue(nullString).getValue();
        assertEquals(" ", result);
    }

}