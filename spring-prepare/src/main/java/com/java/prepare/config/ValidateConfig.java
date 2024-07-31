package com.java.prepare.config;

import org.hibernate.validator.HibernateValidator;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

//@Configuration
public class ValidateConfig {

    //@Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                //开启快速校验--默认校验所有参数，false校验全部
                .addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
