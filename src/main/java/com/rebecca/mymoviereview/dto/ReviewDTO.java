package com.rebecca.mymoviereview.dto;

import com.rebecca.mymoviereview.model.Review;

public class ReviewDTO {

    private Integer id;
    private FilmDTO film;
    private Integer vote;
    private String note;

    public ReviewDTO() {
    }

    public ReviewDTO(Integer id, FilmDTO film, Integer vote, String note) {
        this.id = id;
        this.film = film;
        this.vote = vote;
        this.note = note;
    }

    //dto -> entity
    public Review toEntity() {
        Review review = new Review();
        review.setId(id);
        review.setFilm(this.film.toEntity());
        review.setVote(vote);
        review.setNote(note);
        return review;
    }

    //entity -> dto
    public static ReviewDTO fromEntity(Review review) {
        return new ReviewDTO(review.getId(), FilmDTO.fromEntity(review.getFilm()), review.getVote(), review.getNote());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FilmDTO getFilm() {
        return film;
    }

    public void setFilm(FilmDTO film) {
        this.film = film;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
