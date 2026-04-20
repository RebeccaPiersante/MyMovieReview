package com.rebecca.mymoviereview.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "films")
public class Film {

    @Id
    @Column(name = "id_film")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false, length = 50)
    @NotNull
    @NotBlank
    private String title;

    @Column(name = "duration")
    @Min(0)
    private Integer duration;

    @Column(name = "plot", length = 500)
    private String plot;

    @Column(name = "date_of_publication")
    private LocalDate dateOfPublication;

    @ManyToMany(cascade = {
            CascadeType.PERSIST, //se il regista è nuovo (senza ID), viene salvato nel DB insieme al film
            CascadeType.MERGE    //se il regista esiste (ha un ID), nel db viene aggiornato il legame
    })
    @JoinTable(
            //nome tabella nel db
            name = "film_directors",
            //colonna che punta a questa entity
            joinColumns = @JoinColumn(name = "film_id"),
            //colonna che punta all'altra entity
            inverseJoinColumns = @JoinColumn(name = "director_id")
    )
    private List<Director> directors = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "film_genres",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();

    //cancello film -> cancello recensioni
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    public Film() {
    }

    public Film(Integer id, String title, Integer duration, String plot, LocalDate dateOfPublication) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.plot = plot;
        this.dateOfPublication = dateOfPublication;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
