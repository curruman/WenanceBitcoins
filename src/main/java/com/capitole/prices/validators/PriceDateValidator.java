package com.capitole.prices.validators;

import com.capitole.prices.domain.dto.Price;
import com.capitole.prices.validators.anotation.DateValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;

public class PriceDateValidator implements ConstraintValidator<DateValidator, Price> {

    @Override
    public boolean isValid(Price price, ConstraintValidatorContext constraintValidatorContext) {
        if (isNull(price)) {
            return false;
        }

        if (isNull(price.getStartDate()) || isNull(price.getEndDate()) || isNull(price.getBrandId()) || isNull(price.getProductId())) {
            return false;
        }

        return (price.getStartDate().toLocalDateTime()
                .isAfter(LocalDateTime.now())
                && price.getStartDate().toLocalDateTime()
                .isBefore(price.getEndDate().toLocalDateTime()));
    }
}
