package br.com.equipe4.app_produtos.controller.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


public final class UriUtils {

    private UriUtils() {}

    public static URI criarUriParaRecurso(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("id")
                .buildAndExpand(id)
                .toUri();
    }
}
