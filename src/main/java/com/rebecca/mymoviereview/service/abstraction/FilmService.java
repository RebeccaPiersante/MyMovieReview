package com.rebecca.mymoviereview.service.abstraction;

import com.rebecca.mymoviereview.model.Film;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FilmService {

    List<Film> findAllFilm();

    Optional<Film> findFilmById(int id);

    boolean deleteFilmById(int id);

    boolean updateFilm(int id, Film update);

    Film createFilm(Film create);

    List<Film> findFilmByTitle(String title);

    List<Film> findByDirector(String director);

    List<Film> findByGenre(String genre);

}
