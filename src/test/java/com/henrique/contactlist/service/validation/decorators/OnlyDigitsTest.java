package com.henrique.contactlist.service.validation.decorators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("OnlyDigits test.")
class OnlyDigitsTest {

    @Test
    void shouldRemoveAllLettersFromAStringWithLettersAndDigits() {
        final String singleWord = "1917shfkha2974938 ";
        final String result = new OnlyDigits(new BaseValue(singleWord)).getValue();
        assertEquals("19172974938", result);
    }

    @Test
    void shouldKeepAllDigitsFromAStringWithOnlyDigits() {
        final String singleWord = "19172974938";
        final String result = new OnlyDigits(new BaseValue(singleWord)).getValue();
        assertEquals("19172974938", result);
    }

    @Test
    void shouldRemoveAllSpecialCharsFromAString() {
        final String singleWord = "123!@#$%*(){}.,?./";
        final String result = new OnlyDigits(new BaseValue(singleWord)).getValue();
        assertEquals("123", result);
    }
}