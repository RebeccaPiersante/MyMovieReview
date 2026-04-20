package com.rebecca.mymoviereview.service.implementation;

import com.rebecca.mymoviereview.model.Director;
import com.rebecca.mymoviereview.model.Film;
import com.rebecca.mymoviereview.repository.DirectorRepository;
import com.rebecca.mymoviereview.repository.FilmRepository;
import com.rebecca.mymoviereview.service.abstraction.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceJPA implements FilmService {

    private final FilmRepository repository;
    private final DirectorRepository directorRepository;

    @Autowired
    public FilmServiceJPA(FilmRepository repository, DirectorRepository directorRepository) {
        this.repository = repository;
        this.directorRepository = directorRepository;
    }


    @Override
    public List<Film> findAllFilm() {
        return repository.findAll();
    }

    @Override
    public Optional<Film> findFilmById(int id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public boolean deleteFilmById(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updateFilm(int id, Film update) {
        if (repository.existsById(id) && id == update.getId()) {
            repository.save(update);
            return true;
        }
        return false;
    }

   /*
    Creazione di un nuovo film e la validazione dei registi associati.
    Prima di procede alla creazione di un nuoco regista vengono effettuati controlli:
    se ha un ID viene recuperato dal db altrimenti viene cercato tramite nome e cognome
    per evitare che ci siano duplicati
    */
    @Override
    @Transactional
    public Film createFilm(Film createFilm) {

        List<Director> exists = new ArrayList<>();

        for (Director director : createFilm.getDirectors()) {
            if (director.getId() != null) {
                directorRepository.findById(director.getId()).ifPresent(exists::add);
            } else {
                Optional<Director> od = directorRepository.findByFirstnameIgnoreCaseAndLastnameIgnoreCase(director.getFirstname(), director.getLastname());
                if(od.isPresent()){
                    exists.add(od.get());
                } else {
                    exists.add(director);
                }
            }
        }
        createFilm.setDirectors(exists);
        createFilm.setId(null);
        return repository.save(createFilm);
    }

    @Override
    public List<Film> findFilmByTitle(String title) {
        return repository.findFilmByTitle(title);
    }

    @Override
    public List<Film> findByDirector(String director) {
        return repository.findByDirector(director);
    }

    @Override
    public List<Film> findByGenre(String genre) {
        return repository.findByGenre(genre);
    }
}
