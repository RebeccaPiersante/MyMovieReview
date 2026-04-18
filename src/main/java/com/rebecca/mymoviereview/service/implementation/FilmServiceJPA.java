package com.rebecca.mymoviereview.service.implementation;

import com.rebecca.mymoviereview.model.Film;
import com.rebecca.mymoviereview.repository.FilmRepository;
import com.rebecca.mymoviereview.service.abstraction.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceJPA implements FilmService {

    private final FilmRepository repository;

    @Autowired
    public FilmServiceJPA(FilmRepository repository){
        this.repository = repository;
    }


    @Override
    public List<Film> findAllFilm() {
        List<Film> films = repository.findAll();
        return films;
    }

    @Override
    public Optional<Film> findFilmById(int id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public boolean deleteFilmById(int id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updateFilm(int id, Film update) {
        if(repository.existsById(id) && id == update.getId()){
            repository.save(update);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Film createFilm(Film create) {
        create.setId(null);
        return repository.save(create);
    }
}
