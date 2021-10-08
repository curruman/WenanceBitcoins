package com.capitole.prices.validators.anotation;

import com.capitole.prices.validators.PriceDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.capitole.prices.utils.ConstantsUtils.FORMAT_INVALIDATE;

@Constraint(validatedBy = PriceDateValidator.class)
@Target({ ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateValidator {

    String message() default FORMAT_INVALIDATE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}