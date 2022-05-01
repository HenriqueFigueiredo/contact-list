package com.henrique.contactlist.service.validation.decorators;

import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;

/*
 * Unnecessary complexity in this case. Created only to add some testable classes for example purpose. (Academic stuff)
 */
public class FirstCapitalLetter extends BaseValidableDecorator {

    private static final String SPACE = " ";

    public FirstCapitalLetter(final IValidableField validableValue) {
        super(validableValue);
    }

    @Override
    public String getValue() {
        final String baseValue = super.getValue();
        return Arrays.stream(baseValue.split(SPACE))
                .reduce("", (s, s2) -> aggregateName(capitalizeName(s), capitalizeName(s2)));
    }

    private String aggregateName(final String base, final String name) {
        return base.isBlank() ? name : base + SPACE + name;
    }

    private String capitalizeName(final String name) {
        return Strings.isBlank(name) ? name : name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
