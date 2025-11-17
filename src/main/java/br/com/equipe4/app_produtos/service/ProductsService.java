package br.com.equipe4.app_produtos.service;

import br.com.equipe4.app_produtos.model.Products;
import br.com.equipe4.app_produtos.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductsService {

    private final ProductsRepository productsRepository;

    public Optional<Products> updateProduct(Products product) {
        log.info("Atualizando produto: {}", product);
        final var existingProduct = productsRepository.findById(product.getId());
        existingProduct.ifPresent(p -> {
            product.setBarcode(p.getBarcode());
            product.setName(p.getName());
            product.setPrice(p.getPrice());
            productsRepository.saveAndFlush(product);
        });
        return existingProduct;
    }

    public boolean isOwner(Authentication authentication, Long productId) {
        String currentUsername = authentication.getName();
        Optional<Products> productOpt = productsRepository.findById(productId);
        if (productOpt.isEmpty()) {
            return false;
        }

        String ownerLogin = productOpt.get().getSeller().getUsername();
        return currentUsername.equals(ownerLogin);
    }

}