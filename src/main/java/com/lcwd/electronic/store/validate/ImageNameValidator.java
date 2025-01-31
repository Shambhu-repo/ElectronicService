package com.lcwd.electronic.store.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

public class ImageNameValidator implements ConstraintValidator<ImageNameValid,String> {
private Logger logger = LoggerFactory.getLogger(ImageNameValidator.class);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
//logic
        logger.info("message from isValid()");
        return !value.isBlank();  // it is true if value is not blank

    }
}
