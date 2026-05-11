package com.rebecca.mymoviereview.controller;

import com.rebecca.mymoviereview.dto.GenreDTO;
import com.rebecca.mymoviereview.model.Genre;
import com.rebecca.mymoviereview.service.abstraction.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService service;

    @Autowired
    public GenreController(GenreService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> findAllGenres(){
        List<Genre> genres = service.findAllGenres();
        List<GenreDTO> response = genres.stream().map(GenreDTO::fromEntity).toList();
        return ResponseEntity.ok(response);
    }
}
