package com.rebecca.mymoviereview.service.abstraction;

import com.rebecca.mymoviereview.model.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findAllGenres();
}
