package com.capitole.prices.validators;

import com.capitole.prices.validators.anotation.ConsistentDateParameters;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.time.LocalDateTime;

import static com.capitole.prices.utils.ConstantsUtils.ILEGAL_PARAMETER_DATE_ERROR;


@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ConsistentDateParameterValidator implements ConstraintValidator<ConsistentDateParameters, Object[]> {

    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        if (value[2] == null) {
            return false;
        }

        if (!(value[2] instanceof LocalDateTime)) {
            throw new IllegalArgumentException(ILEGAL_PARAMETER_DATE_ERROR);
        }else return true;

        //return ((LocalDateTime) value[0]).isAfter(LocalDateTime.now()) && ((LocalDateTime) value[0]).isBefore((LocalDateTime) value[1]);
    }
}