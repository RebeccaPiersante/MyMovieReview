package com.rebecca.mymoviereview.repository;

import com.rebecca.mymoviereview.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {

    Optional<Director> findByFirstnameIgnoreCaseAndLastnameIgnoreCase(String firstname, String lastname);

}
