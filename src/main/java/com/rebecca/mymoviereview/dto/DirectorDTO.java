package com.rebecca.mymoviereview.dto;

import com.rebecca.mymoviereview.model.Director;
import com.rebecca.mymoviereview.model.Film;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DirectorDTO {

    private Integer id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String nationality;
    private List<String> filmTitle = new ArrayList<>();

    public DirectorDTO() {}

    public DirectorDTO(Integer id, String firstname, String lastname, LocalDate birthdate,
                       String nationality, List<String> filmTitle) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.filmTitle = filmTitle;
    }

    //dto -> entity
    public Director toEntity() {
        return new Director(id, firstname, lastname, birthdate, nationality);
    }

    //entity -> dto
    public static DirectorDTO fromEntity(Director director) {
        List<String> titles = director.getFilms().stream().map(Film::getTitle).toList();
        return new DirectorDTO(director.getId(), director.getFirstname(), director.getLastname(), director.getBirthdate(),
                director.getNationality(), titles);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<String> getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(List<String> filmTitle) {
        this.filmTitle = filmTitle;
    }
}
