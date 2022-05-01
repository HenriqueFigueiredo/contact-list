package com.henrique.contactlist.service.validation.decorators;

/*
 * Unnecessary complexity in this case. Created only to add some testable classes for example purpose. (Academic stuff)
 */
public class OnlyDigits extends BaseValidableDecorator {

    public OnlyDigits(final IValidableField validableValue) {
        super(validableValue);
    }

    @Override
    public String getValue() {
        final String baseValue = super.getValue();
        return baseValue.replaceAll("[^0-9]", "");
    }
}
