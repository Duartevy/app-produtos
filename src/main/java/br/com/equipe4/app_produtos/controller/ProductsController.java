package br.com.equipe4.app_produtos.controller;

import br.com.equipe4.app_produtos.model.Products;
import br.com.equipe4.app_produtos.model.User;
import br.com.equipe4.app_produtos.repository.ProductsRepository;
import br.com.equipe4.app_produtos.service.ProductsService;
import br.com.equipe4.app_produtos.service.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductsController {

    private final ProductsRepository productsRepository;
    private final ProductsService productsService;

    @PostMapping("product")
    @PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
    public ResponseEntity<Products> createProduct(@RequestBody Products product) {
        User seller = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        product.setSeller(seller);
        Products saved = productsRepository.save(product);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Products>> listProducts() {
        List<Products> products = productsRepository.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id) {
        Products product = productsRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(product);
    }

    /**
     * Exemplo de retorno de um Record.
     * @param id
     * @return
     */
    @GetMapping("/dto/{id}")
    public ResponseEntity<ProductDto> getProductDtoById(@PathVariable Long id) {
        ProductDto productDto = productsRepository.findByIdDto(id);

        final var product = new Products();
        product.setName(productDto.name());
        product.setPrice(productDto.price());
        product.setBarcode(productDto.barcode());
        User seller = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        product.setSeller(seller);
        productsRepository.saveAndFlush(product);

        return ResponseEntity.ok(productDto);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN') or @productsService.isOwner(authentication, #product.id)")
    public ResponseEntity<Optional<Products>> updateProduct(@RequestBody Products product) {
        final var existingProduct = productsService.updateProduct(product);
        return ResponseEntity.ok(existingProduct);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @productsService.isOwner(authentication, #id)")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}