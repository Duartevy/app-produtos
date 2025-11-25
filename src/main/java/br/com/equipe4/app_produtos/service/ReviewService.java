package br.com.equipe4.app_produtos.service;

import br.com.equipe4.app_produtos.infra.exceptions.EntityNotFoundException;
import br.com.equipe4.app_produtos.mapper.ReviewMapper;
import br.com.equipe4.app_produtos.model.Review;
import br.com.equipe4.app_produtos.repository.ReviewRepository;
import br.com.equipe4.app_produtos.service.dto.request.ReviewRequestDTO;
import br.com.equipe4.app_produtos.service.dto.response.ReviewResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper mapper;


    public ReviewResponseDTO create(ReviewRequestDTO dto) {
        validateRating(dto.rating());

        Review entity = mapper.toEntity(dto);
        Review saved = reviewRepository.save(entity);

        return mapper.toResponse(saved);
    }

    // FIND BY ID
    public ReviewResponseDTO findReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found."));
        return mapper.toResponse(review);
    }

    // FIND BY PRODUCT
    public List<ReviewResponseDTO> findByProductId(Long productId) {
        return reviewRepository.findByProductId(productId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // FIND BY USER
    public List<ReviewResponseDTO> findByUserId(Long userId) {
        return reviewRepository.findByUserId(userId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // UPDATE
    public ReviewResponseDTO update(Long id, ReviewRequestDTO dto) {
        validateRating(dto.rating());

        Review existing = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found."));

        existing.setRating(dto.rating());
        existing.setComment(dto.comment());

        Review updated = reviewRepository.save(existing);
        return mapper.toResponse(updated);
    }

    // VALIDATION
    private void validateRating(Integer rating) {
        if (rating == null || rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
    }

    public void delete(Long id) {
        reviewRepository.findById(id).ifPresentOrElse(reviewRepository::delete, () -> {
            throw new EntityNotFoundException("Review not found.");
        });


    }
}
