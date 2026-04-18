package com.rebecca.mymoviereview.repository;

import com.rebecca.mymoviereview.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {

    @Query("""
            SELECT DISTINCT f
            FROM Film f
            WHERE LOWER(f.title) LIKE LOWER(CONCAT('%', :title, '%'))
            """)
    List<Film> findFilmByTitle(@Param("title") String title);

    @Query("""
            SELECT DISTINCT f
            FROM Film f
            JOIN f.directors d
            WHERE LOWER(CONCAT(d.firstname, ' ', d.lastname)) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<Film> findByDirector(@Param("name") String director);

    @Query("""
            SELECT DISTINCT f
            FROM Film f
            JOIN f.genres g
            WHERE LOWER(STR(g.genre)) LIKE LOWER(CONCAT('%', :genre, '%'))
            """)
    List<Film> findByGenre(@Param("genre") String genre);

}
