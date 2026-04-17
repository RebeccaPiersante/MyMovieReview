package com.rebecca.mymoviereview.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "directors")
public class Director {

    @Id
    @Column(name = "id_director")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstname", nullable = false, length = 50)
    @NotBlank
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 50)
    @NotBlank
    private String lastname;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "nationality", length = 100)
    private String nationality;

    //nome del campo nella entiity Film
    @ManyToMany(mappedBy = "directors")
    @JsonIgnore
    private List<Film> films = new ArrayList<>();

    public Director(){}

    public Director(Integer id, String firstname, String lastname, LocalDate birthdate, String nationality) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.nationality = nationality;
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

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
