package com.henrique.contactlist.service.validation.decorators;

/*
 * Unnecessary complexity in this case. Created only to add some testable classes for example purpose. (Academic stuff)
 */
public class BaseValue implements IValidableField {

    private final String value;

    public BaseValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value != null ? this.value : "";
    }
}
