# MyMovieReview

**MyMovieReview** è un'applicazione che permette all'utente di catalogare i film visti, associarli ai relativi registi e generi, e scrivere recensioni dettagliate con votazione.

## TECNOLOGIE USATE 
- Java
- Spring Boot
- Spring Web (MVC)
- Spring Data JPA
- PostgreSQL
- Validation
- DevTools

## DATABASE

Il progetto utilizza un database PostgreSQL composto da 4 tabelle principali e 2 tabelle ponte, che implementano diverse tipologie di relazioni:

1.  **Films:** entità centrale dell'applicazione.
2.  **Directors:** gestione dei registi (relazione Many-to-Many con Films).
3.  **Genres:** catalogazione tramite Enum (relazione Many-to-Many con Films).
4.  **Reviews:** recensioni personali (relazione Many-to-One con Films).

### Relazioni implementate:
- **Many-to-Many (N:N):** gestita tramite tabelle ponte (`film_directors` e `film_genres`) per permettere a un film di avere più registi/generi e viceversa.
- **One-to-Many (1:N):** un film può contenere diverse recensioni personali nel tempo.

### SCHEMA SQL

<details>
<summary><b>Clicca qui per visualizzare le tabelle</b></summary>

```sql
CREATE TABLE films (
	id_film  SERIAL,
	title    VARCHAR(50) NOT NULL,
	duration INT,
	plot     VARCHAR(500),
	date_of_publication DATE,
	
	CONSTRAINT pk_films PRIMARY KEY(id_film)
);

CREATE TABLE directors (
	id_director SERIAL,
	firstname   VARCHAR(50) NOT NULL,
	lastname    VARCHAR(50) NOT NULL,
	birthdate   DATE,
	nationality VARCHAR(100),
	
	CONSTRAINT pk_directors PRIMARY KEY(id_director)
);

CREATE TABLE genres (
	id_genre SERIAL,
	genre    VARCHAR(100) NOT NULL,
	
	CONSTRAINT pk_genres PRIMARY KEY(id_genre)
);

CREATE TABLE reviews (
	id_review SERIAL,
	film_id   INT,
	vote      INT NOT NULL CHECK(vote >= 0 AND vote <= 10),
	note      VARCHAR(300),
	
	CONSTRAINT pk_reviews PRIMARY KEY(id_review),
	CONSTRAINT fk_films FOREIGN KEY(film_id)
	REFERENCES films(id_film) ON DELETE CASCADE
	
); 

CREATE TABLE film_directors(
	film_id     INT,
	director_id INT,
	
	 CONSTRAINT pk_film_directors PRIMARY KEY (film_id, director_id),
   CONSTRAINT fk_film_director FOREIGN KEY (film_id) REFERENCES films(id_film) ON DELETE CASCADE,
   CONSTRAINT fk_director_film FOREIGN KEY (director_id) REFERENCES directors(id_director) ON DELETE CASCADE
	
);

CREATE TABLE film_genres(
	film_id  INT,
	genre_id INT,
	
	CONSTRAINT pk_film_genres PRIMARY KEY (film_id, genre_id),
	CONSTRAINT fk_film_genre FOREIGN KEY (film_id) REFERENCES films(id_film) ON DELETE CASCADE,
  CONSTRAINT fk_genre_film FOREIGN KEY (genre_id) REFERENCES genres(id_genre) ON DELETE CASCADE
	
);
```
</details>

## STATO DEL PROGETTO

***in fase di sviluppo***

Attualmente implementato:

- Setup progetto
- Configurazione database
- Mapping ORM

### Miglioramenti Previsti:
**Interfaccia Frontend:** Sviluppo di una UI semplice e funzionale utilizzando esclusivamente **HTML, CSS e JavaScript**.

*Progetto sviluppato a scopo didattico.*
