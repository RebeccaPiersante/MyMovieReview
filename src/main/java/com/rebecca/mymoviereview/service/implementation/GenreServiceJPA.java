package com.rebecca.mymoviereview.service.implementation;

import com.rebecca.mymoviereview.model.Genre;
import com.rebecca.mymoviereview.repository.GenreRepository;
import com.rebecca.mymoviereview.service.abstraction.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceJPA implements GenreService {

    private final GenreRepository repository;

    @Autowired
    public GenreServiceJPA(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Genre> findAllGenres() {
        return repository.findAll();
    }
}
