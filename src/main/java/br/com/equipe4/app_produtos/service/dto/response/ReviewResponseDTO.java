package br.com.equipe4.app_produtos.service.dto.response;

import java.time.LocalDateTime;

public record ReviewResponseDTO(
        Long id,
        Long productId,
        Long userId,
        Integer rating,
        String comment,
        LocalDateTime createdAt
) {}
