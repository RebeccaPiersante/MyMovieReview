package com.rebecca.mymoviereview.service.abstraction;


import com.rebecca.mymoviereview.model.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorService {

    List<Director> findAllDirector();
    Optional<Director> findDirectorById(int id);
    boolean deleteDirectorById(int id);
    boolean updateDirector(int id, Director update);
    Director createDirector(Director create);
}
