package com.movie4u.sep.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "directors")
public class DirectorEntity {
    @EmbeddedId
    private DirectorMovieKey id;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private MovieEntity movieEntity;

    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private PeopleEntity director;

    public DirectorMovieKey getId() {
        return id;
    }

    public MovieEntity getMovie() {
        return movieEntity;
    }

    public PeopleEntity getDirector() {
        return director;
    }

}
