package com.rebecca.mymoviereview.dto;

import com.rebecca.mymoviereview.model.Director;
import com.rebecca.mymoviereview.model.Film;
import com.rebecca.mymoviereview.model.Genre;

import java.time.LocalDate;
import java.util.List;

public class FilmDTO {

    private Integer id;
    private String title;
    private int duration;
    private String plot;
    private LocalDate dateOfPublication;

    private List<Director> directors;
    private List<Genre> genres;

    public FilmDTO() {
    }

    public FilmDTO(Integer id, String title, int duration, String plot, LocalDate dateOfPublication,
                   List<Director> directors, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.plot = plot;
        this.dateOfPublication = dateOfPublication;
        this.directors = directors;
        this.genres = genres;
    }

    //dto -> entity
    public Film toEntity() {
        Film film = new Film(id, title, duration, plot, dateOfPublication);
        film.setDirectors(directors);
        film.setGenres(genres);
        return film;
    }

    //entity -> dto
    public static FilmDTO fromEntity(Film film) {
        return new FilmDTO(film.getId(), film.getTitle(), film.getDuration(), film.getPlot(), film.getDateOfPublication(),
                film.getDirectors(), film.getGenres());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public LocalDate getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(LocalDate dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}

