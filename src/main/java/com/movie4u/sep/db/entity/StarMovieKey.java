package com.movie4u.sep.db.entity;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class StarMovieKey implements Serializable {
    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "person_id")
    private int personId;

    public int getMovieId() {
        return movieId;
    }

    public int getPersonId() {
        return personId;
    }

}
