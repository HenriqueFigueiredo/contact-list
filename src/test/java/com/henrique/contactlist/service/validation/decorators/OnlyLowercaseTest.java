package com.henrique.contactlist.service.validation.decorators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("OnlyLowercase test.")
class OnlyLowercaseTest {

    @Test
    void shouldConvertAllUpperLettersFromASingleWord() {
        final String singleWord = "AaBbCcDEF ";
        final String result = new OnlyLowercase(new BaseValue(singleWord)).getValue();
        assertEquals("aabbccdef ", result);
    }

    @Test
    void shouldConvertAllUpperLettersFromSeveralWords() {
        final String severalWords = "AaBbCcDEF AaBbCcDEF";
        final String result = new OnlyLowercase(new BaseValue(severalWords)).getValue();
        assertEquals("aabbccdef aabbccdef", result);
    }

}