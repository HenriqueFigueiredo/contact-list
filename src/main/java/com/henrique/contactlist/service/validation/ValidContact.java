package com.henrique.contactlist.service.validation;

import com.henrique.contactlist.service.validation.decorators.*;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ValidContact {

    private final String name;
    private final String phone;
    private final String email;
    private final String uuid;

    public ValidContact(String name, String phone, String email) {
        this.name = new FirstCapitalLetter(new OnlyLowercase(new BaseValue(name))).getValue();
        this.phone = new OnlyDigits(new BaseValue(phone)).getValue();
        this.email = new NoSpaces(new OnlyLowercase(new BaseValue(email))).getValue();
        this.uuid = UUID.randomUUID().toString();
    }
}
