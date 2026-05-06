package com.rebecca.mymoviereview.repository;

import com.rebecca.mymoviereview.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {

    //per evitare i duplicati quando creo un film
    Optional<Director> findByFirstnameIgnoreCaseAndLastnameIgnoreCase(String firstname, String lastname);

    //per la ricerca
    List<Director> findByLastnameContainingIgnoreCase(String lastname);

    List<Director> findByFirstnameContainingIgnoreCaseAndLastnameContainingIgnoreCase(String firstname, String lastname);

    List<Director> findByFirstnameContainingIgnoreCase(String firstname);
}
