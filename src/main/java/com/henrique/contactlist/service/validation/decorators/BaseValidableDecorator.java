package com.henrique.contactlist.service.validation.decorators;

/*
 * Unnecessary complexity in this case. Created only to add some testable classes for example purpose. (Academic stuff)
 */
public abstract class BaseValidableDecorator implements IValidableField {

    private final IValidableField validableValue;

    protected BaseValidableDecorator(final IValidableField validableValue) {
        this.validableValue = validableValue;
    }
    @Override
    public String getValue() {
        return this.validableValue.getValue();
    }
}
