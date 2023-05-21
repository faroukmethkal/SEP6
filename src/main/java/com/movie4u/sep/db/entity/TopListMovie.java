package com.movie4u.sep.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "top_list_movie",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"movie_id", "user_id"})
        })
public class TopListMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "user_id")
    private Integer userId;

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

    public int getUserId() {
        return userId;
    }

    public void setPeopleId(int UserId) {
        this.userId = UserId;
    }
}
