package br.com.equipe4.app_produtos.service.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(@NotBlank(message = "Nome é obrigatório") String name,
        Long parentId) {

}
