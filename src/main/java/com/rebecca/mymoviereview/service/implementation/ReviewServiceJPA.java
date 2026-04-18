package com.rebecca.mymoviereview.service.implementation;

import com.rebecca.mymoviereview.model.Review;
import com.rebecca.mymoviereview.repository.ReviewRepository;
import com.rebecca.mymoviereview.service.abstraction.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceJPA implements ReviewService {

    private final ReviewRepository repository;

    @Autowired
    public ReviewServiceJPA(ReviewRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Review> findAllReview() {
        return repository.findAll();
    }

    @Override
    public Optional<Review> findReviewById(int id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public boolean deleteReview(int id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updateReview(int id, Review update) {
        if(repository.existsById(id) && id == update.getId()){
            repository.save(update);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Review createReview(Review create) {
        create.setId(null);
        return repository.save(create);
    }
}
