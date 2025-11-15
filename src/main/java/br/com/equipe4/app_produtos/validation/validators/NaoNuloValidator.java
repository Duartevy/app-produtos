package br.com.equipe4.app_produtos.validation.validators;

import br.com.equipe4.app_produtos.validation.Annotations.NaoNulo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NaoNuloValidator implements ConstraintValidator<NaoNulo, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return value != null;
    }
}
