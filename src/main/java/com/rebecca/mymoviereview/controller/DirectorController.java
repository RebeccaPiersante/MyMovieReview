package com.rebecca.mymoviereview.controller;

import com.rebecca.mymoviereview.dto.DirectorDTO;
import com.rebecca.mymoviereview.model.Director;
import com.rebecca.mymoviereview.service.abstraction.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.rebecca.mymoviereview.dto.DirectorDTO.fromEntity;

@RestController
@RequestMapping("/api/director")
public class DirectorController {

    private final DirectorService service;

    @Autowired
    public DirectorController(DirectorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DirectorDTO>> findAllDirector(@RequestParam(required = false) String firstname,
                                                             @RequestParam(required = false) String lastname) {
        List<Director> directorFound;

        if (firstname != null && !firstname.isBlank() && lastname != null && !lastname.isBlank()) {
            directorFound = service.findByFirstnameContainingIgnoreCaseAndLastnameContainingIgnoreCase(firstname, lastname);
        } else if (firstname != null && !firstname.isBlank()) {
            directorFound = service.findByFirstnameContainingIgnoreCase(firstname);
        } else if (lastname != null && !lastname.isBlank()) {
            directorFound = service.findByLastnameContainingIgnoreCase(lastname);
        } else {
            directorFound= service.findAllDirector();
        }

        List<DirectorDTO> response = directorFound.stream().map(DirectorDTO::fromEntity).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findDirectorById(@PathVariable int id) {
        Optional<Director> od = service.findDirectorById(id);
        if (od.isPresent()) {
            return ResponseEntity.ok(fromEntity(od.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDirector(@PathVariable int id) {
        boolean deleted = service.deleteDirectorById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DirectorDTO> createDirector(@RequestBody DirectorDTO dto) {

        Director director = dto.toEntity();
        Director create = service.createDirector(director);
        DirectorDTO response = fromEntity(create);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDirector(@PathVariable int id, @RequestBody DirectorDTO dto) {

        if(dto.getId() == null || dto.getId() != id){
            return ResponseEntity.badRequest().build();
        }

        Director director = dto.toEntity();
        boolean update = service.updateDirector(id, director);
        if(update) {
            return ResponseEntity.ok(fromEntity(director));
        }
        return ResponseEntity.notFound().build();

    }

}
