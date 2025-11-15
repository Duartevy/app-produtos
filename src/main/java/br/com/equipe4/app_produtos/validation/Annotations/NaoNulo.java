package br.com.equipe4.app_produtos.validation.Annotations;

import br.com.equipe4.app_produtos.validation.validators.NaoNuloValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = NaoNuloValidator.class)
public @interface NaoNulo {
    String message() default "O valor não pode ser nulo";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}