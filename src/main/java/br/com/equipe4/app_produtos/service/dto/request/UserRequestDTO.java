package br.com.equipe4.app_produtos.service.dto.request;

import br.com.equipe4.app_produtos.validation.Annotations.NaoNulo;
import br.com.equipe4.app_produtos.validation.Annotations.NaoVazioSeInformado;
import jakarta.validation.constraints.Email;

public record UserRequestDTO(
        @NaoVazioSeInformado(message = "Campo obrigatório!")
        @NaoNulo(message = "Campo obrigatório!")
        String name,
        @NaoNulo(message = "Campo obrigatório!")
        @Email(message = "Digite um email válido!")
        String email,
        @NaoVazioSeInformado(message = "Campo obrigatório!")
        @NaoNulo(message = "Campo obrigatório!")
        String password) {
}
