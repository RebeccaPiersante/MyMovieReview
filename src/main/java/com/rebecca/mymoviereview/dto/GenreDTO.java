package com.rebecca.mymoviereview.dto;

import com.rebecca.mymoviereview.model.Genre;
import com.rebecca.mymoviereview.model.Genres;

public class GenreDTO {

    private Integer id;
    private String genre;

    public GenreDTO() {
    }

    public GenreDTO(Integer id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    //entity -> dto
    public static GenreDTO fromEntity(Genre genre) {
        return new GenreDTO(genre.getId(), genre.getGenre().name());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
