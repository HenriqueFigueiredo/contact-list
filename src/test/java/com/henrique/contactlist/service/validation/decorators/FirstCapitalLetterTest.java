package com.henrique.contactlist.service.validation.decorators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("FirstCapitalLetter test.")
class FirstCapitalLetterTest {

    @Test
    void shouldConvertTheFirstLetterOfASingleWord() {
        final String singleWord = "henrique";
        final String result = new FirstCapitalLetter(new BaseValue(singleWord)).getValue();
        assertEquals("Henrique", result);
    }

    @Test
    void shouldConvertTheFirstLetterOfMultiplesWords() {
        final String severalWords = "henrique silva figueiredo";
        final String result = new FirstCapitalLetter(new BaseValue(severalWords)).getValue();
        assertEquals("Henrique Silva Figueiredo", result);
    }

    @Test
    void shouldMustTheFirstCapitalLetterOfAWord() {
        final String singleWordFirstCapitalLetter = "HenriquE";
        final String result = new FirstCapitalLetter(new BaseValue(singleWordFirstCapitalLetter)).getValue();
        assertEquals("HenriquE", result);
    }

}