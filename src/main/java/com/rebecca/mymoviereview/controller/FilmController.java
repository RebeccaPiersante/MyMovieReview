package com.rebecca.mymoviereview.controller;

import com.rebecca.mymoviereview.dto.FilmDTO;
import com.rebecca.mymoviereview.model.Film;
import com.rebecca.mymoviereview.service.abstraction.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.rebecca.mymoviereview.dto.FilmDTO.fromEntity;

@RestController
@RequestMapping("/api/film")
public class FilmController {

    private final FilmService service;

    @Autowired
    public FilmController(FilmService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FilmDTO>> globalSearch(@RequestParam(required = false) String title,
                                                      @RequestParam(required = false) String director,
                                                      @RequestParam(required = false) String genre) {

        List<Film> moviesFound;

        if (title != null && !title.isBlank()) {
            moviesFound = service.findFilmByTitle(title);
        } else if (director != null && !director.isBlank()) {
            moviesFound = service.findByDirector(director);
        } else if (genre != null && !genre.isBlank()) {
            moviesFound = service.findByGenre(genre);
        } else {
            moviesFound = service.findAllFilm();
        }

        List<FilmDTO> response = moviesFound.stream().map(FilmDTO::fromEntity).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findFilmById(@PathVariable int id) {
        Optional<Film> optionalFilm = service.findFilmById(id);
        if (optionalFilm.isPresent()) {
            return ResponseEntity.ok(fromEntity(optionalFilm.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFilmById(@PathVariable int id) {
        boolean deleted = service.deleteFilmById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<FilmDTO> createFilm(@RequestBody FilmDTO dto) {
        Film film = dto.toEntity();
        Film create = service.createFilm(film);
        FilmDTO response = fromEntity(create);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFilm(@PathVariable int id, @RequestBody FilmDTO filmDTO) {
        if (filmDTO.getId() == null || filmDTO.getId() != id) {
            return ResponseEntity.badRequest().build();
        }

        Film film = filmDTO.toEntity();
        boolean update = service.updateFilm(id, film);
        if (update) {
            return ResponseEntity.ok(fromEntity(film));
        }
        return ResponseEntity.notFound().build();
    }
}
