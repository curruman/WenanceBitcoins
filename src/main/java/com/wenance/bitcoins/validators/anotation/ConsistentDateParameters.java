package com.wenance.bitcoins.validators.anotation;

import com.wenance.bitcoins.validators.ConsistentDateParameterValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.wenance.bitcoins.utils.ConstantsUtils.END_DATE_LESS_THAT_START_DATE_ERROR;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ConsistentDateParameterValidator.class)
@Target({ METHOD, CONSTRUCTOR })
@Retention(RUNTIME)
public @interface ConsistentDateParameters {
    String message() default END_DATE_LESS_THAT_START_DATE_ERROR;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
