package br.com.equipe4.app_produtos.controller;

import br.com.equipe4.app_produtos.controller.utils.UriUtils;
import br.com.equipe4.app_produtos.service.ReviewService;
import br.com.equipe4.app_produtos.service.dto.request.ReviewRequestDTO;
import br.com.equipe4.app_produtos.service.dto.response.ReviewResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> create(@RequestBody ReviewRequestDTO reviewRequestDTO) {
        ReviewResponseDTO reviewResponseDTO = reviewService.create(reviewRequestDTO);
        URI uri = UriUtils.criarUriParaRecurso(reviewResponseDTO.id());
        return ResponseEntity.created(uri).body(reviewResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.findReviewById(id));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewResponseDTO>> findByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.findByProductId(productId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewResponseDTO>> findByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.findByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> update(@PathVariable Long id, @RequestBody ReviewRequestDTO review) {
        return ResponseEntity.ok(reviewService.update(id, review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

