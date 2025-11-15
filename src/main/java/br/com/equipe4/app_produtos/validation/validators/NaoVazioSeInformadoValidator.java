package br.com.equipe4.app_produtos.validation.validators;

import br.com.equipe4.app_produtos.validation.Annotations.NaoVazioSeInformado;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NaoVazioSeInformadoValidator implements ConstraintValidator<NaoVazioSeInformado, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return true; // null é permitido
        }

        return !value.trim().isEmpty();
    }
}
