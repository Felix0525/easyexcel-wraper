package com.wuyue.excel;

import org.hibernate.validator.HibernateValidator;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ExcelValidator {

    private static Validator validator = null;

    static {
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    private ExcelValidator() {

    }

    public static Validator getValidator() {
        return validator;
    }

}
