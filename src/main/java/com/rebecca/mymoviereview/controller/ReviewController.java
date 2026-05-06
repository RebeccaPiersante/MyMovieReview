package com.rebecca.mymoviereview.controller;

import com.rebecca.mymoviereview.dto.ReviewDTO;
import com.rebecca.mymoviereview.model.Review;
import com.rebecca.mymoviereview.service.abstraction.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.rebecca.mymoviereview.dto.ReviewDTO.fromEntity;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService service;

    @Autowired
    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> findAllReview() {
        List<Review> reviews = service.findAllReview();
        List<ReviewDTO> response = reviews.stream().map(ReviewDTO::fromEntity).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findReviewById(@PathVariable int id) {
        Optional<Review> or = service.findReviewById(id);
        if (or.isPresent()) {
            return ResponseEntity.ok(fromEntity(or.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable int id) {
        boolean deleted = service.deleteReview(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@PathVariable int id,
                                          @RequestBody ReviewDTO dto) {
        if (dto.getId() == null || dto.getId() != id) {
            return ResponseEntity.badRequest().build();
        }
        Review review = dto.toEntity();
        boolean update = service.updateReview(id, review);
        if (update) {
            return ResponseEntity.ok(fromEntity(review));
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO dto) {
        Review review = dto.toEntity();
        Review create = service.createReview(review);
        if (create == null) {
            return ResponseEntity.badRequest().build();
        }
        ReviewDTO response = fromEntity(create);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
