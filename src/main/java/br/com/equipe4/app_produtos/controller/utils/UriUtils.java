package br.com.equipe4.app_produtos.controller.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public interface UriUtils {

    default URI criarUriParaRecurso(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("id")
                .buildAndExpand(id)
                .toUri();
    }
}
