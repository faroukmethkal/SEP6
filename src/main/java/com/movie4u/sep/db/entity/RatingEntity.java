package com.movie4u.sep.db.entity;

import com.movie4u.sep.db.entity.MovieEntity;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;
    private float rating;
    private int votes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private MovieEntity movieEntity;

    public int getMovieId() {
        return movieId;
    }

    public float getRating() {
        return rating;
    }

    public int getVotes() {
        return votes;
    }

    public MovieEntity getMovie() {
        return movieEntity;
    }

}
