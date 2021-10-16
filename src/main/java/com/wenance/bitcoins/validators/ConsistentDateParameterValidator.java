package com.wenance.bitcoins.validators;

import com.wenance.bitcoins.validators.anotation.ConsistentDateParameters;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.time.LocalDateTime;

import static com.wenance.bitcoins.utils.ConstantsUtils.ILEGAL_PARAMETER_DATE_ERROR;
import static java.util.Objects.isNull;


@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ConsistentDateParameterValidator implements ConstraintValidator<ConsistentDateParameters, Object[]> {

    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        if(value.length==2){
            return validateTwoDates(value);
        }

        if (value[0] == null) {
            return false;
        }

        if (!(value[0] instanceof LocalDateTime)) {
            throw new IllegalArgumentException(ILEGAL_PARAMETER_DATE_ERROR);
        }else return true;

    }

    private boolean validateTwoDates(Object[] value){
            if (isNull(value[0]) || isNull(value[1])) {
                return false;
            }
            LocalDateTime startDate;
            LocalDateTime endDate;

            if (!(value[0] instanceof LocalDateTime) || !(value[1] instanceof LocalDateTime)) {
                throw new IllegalArgumentException(ILEGAL_PARAMETER_DATE_ERROR);
            }else {
                startDate=(LocalDateTime) value[0];
                endDate=(LocalDateTime) value[1];
            }

            if(startDate.isAfter(LocalDateTime.now())){
                return false;
            }

            if(endDate.isBefore(startDate)){
                return false;
            }else return true;
    }
}