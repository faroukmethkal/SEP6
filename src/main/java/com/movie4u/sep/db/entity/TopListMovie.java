package com.movie4u.sep.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "top_list_movie",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"movie_id", "username"})
        })
public class TopListMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "username")
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
