package com.wuyue.excel.validate;

import javax.validation.Validation;
import javax.validation.Validator;

public class HibernateValidator {

    private static Validator validator = Validation.byProvider( org.hibernate.validator.HibernateValidator.class )
            .configure()
            .addProperty( "hibernate.validator.fail_fast", "true" )
            .buildValidatorFactory().getValidator();

    private HibernateValidator() {

    }

    public static Validator getValidator() {
        return validator;
    }

}
