package br.com.equipe4.app_produtos.service.dto.request;

import br.com.equipe4.app_produtos.validation.Annotations.NaoNulo;
import br.com.equipe4.app_produtos.validation.Annotations.NaoVazioSeInformado;
import jakarta.validation.constraints.Email;

public record UsuarioRequestDto(
        @NaoVazioSeInformado(message = "Campo obrigatório!")
        @NaoNulo(message = "Campo obrigatório!")
        String nome,
        @NaoNulo(message = "Campo obrigatório!")
        @Email(message = "Digite um email válido!")
        String email,
        @NaoVazioSeInformado(message = "Campo obrigatório!")
        @NaoNulo(message = "Campo obrigatório!")
        String senha) {
}
