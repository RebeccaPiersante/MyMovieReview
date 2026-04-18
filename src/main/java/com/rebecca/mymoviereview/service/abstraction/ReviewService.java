package com.rebecca.mymoviereview.service.abstraction;

import com.rebecca.mymoviereview.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<Review> findAllReview();
    Optional<Review> findReviewById(int id);
    boolean deleteReview(int id);
    boolean updateReview(int id, Review update);
    Review createReview(Review create);

}
