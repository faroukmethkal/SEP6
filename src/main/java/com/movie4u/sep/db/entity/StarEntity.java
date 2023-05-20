package com.movie4u.sep.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "stars")
public class StarEntity {

    @EmbeddedId
    private StarMovieKey id;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private MovieEntity movieEntity;

    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private PeopleEntity star;

    public StarMovieKey getId() {
        return id;
    }

    public MovieEntity getMovie() {
        return movieEntity;
    }

    public PeopleEntity getStar() {
        return star;
    }

}


