package br.com.equipe4.app_produtos.validation.Annotations;

import br.com.equipe4.app_produtos.validation.validators.NaoVazioSeInformadoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = NaoVazioSeInformadoValidator.class)
public @interface NaoVazioSeInformado {
    String message() default "Se informado, o valor não pode ser vazio";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
