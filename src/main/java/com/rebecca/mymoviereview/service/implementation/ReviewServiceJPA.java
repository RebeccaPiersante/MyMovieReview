package com.rebecca.mymoviereview.service.implementation;

import com.rebecca.mymoviereview.model.Film;
import com.rebecca.mymoviereview.model.Review;
import com.rebecca.mymoviereview.repository.FilmRepository;
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
    private final FilmRepository filmRepository;

    @Autowired
    public ReviewServiceJPA(ReviewRepository repository, FilmRepository filmRepository){
        this.repository = repository;
        this.filmRepository = filmRepository;
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

    /*
    controllo che il film esista nel DB tramite findById(Film) se esiste collego la review al film
    e salvo la review altrimenti, se il film non esiste, restituisco null
     */
    @Override
    @Transactional
    public Review createReview(Review create) {
        if (create.getFilm() == null || create.getFilm().getId() == null) {
            return null;
        }

        Optional<Film> filmOptional = filmRepository.findById(create.getFilm().getId());

        if (filmOptional.isPresent()) {
            create.setFilm(filmOptional.get());
            create.setId(null);
            return repository.save(create);
        }

        return null;
    }
}
