package com.rebecca.mymoviereview.controller;

import com.rebecca.mymoviereview.dto.FilmDTO;
import com.rebecca.mymoviereview.model.Film;
import com.rebecca.mymoviereview.service.abstraction.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.rebecca.mymoviereview.dto.FilmDTO.fromEntity;

@RestController
@RequestMapping("/api/film")
public class FilmController {

    private final FilmService service;

    @Autowired
    public FilmController(FilmService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<FilmDTO> createFilm(@RequestBody FilmDTO dto) {
        Film film = dto.toEntity();
        Film create = service.createFilm(film);
        FilmDTO response = fromEntity(create);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
