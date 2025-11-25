package br.com.equipe4.app_produtos.service.dto.request;

import br.com.equipe4.app_produtos.validation.Annotations.NaoNulo;
import br.com.equipe4.app_produtos.validation.Annotations.NaoVazioSeInformado;

public record ReviewRequestDTO(
        @NaoNulo(message = "required field.")
        @NaoVazioSeInformado(message = "required field.")
        Long productId,
        @NaoNulo(message = "required field.")
        @NaoVazioSeInformado(message = "required field.")
        Long userId,
        @NaoNulo(message = "required field.")
        @NaoVazioSeInformado(message = "required field.")
        Integer rating,
        @NaoVazioSeInformado(message = "required field.")
        String comment
) {}
