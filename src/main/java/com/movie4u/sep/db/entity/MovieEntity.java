package com.movie4u.sep.db.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "movies")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int year;

    @OneToMany(mappedBy = "movieEntity", fetch = FetchType.EAGER)
    private Set<StarEntity> starEntities;

    @OneToMany(mappedBy = "movieEntity", fetch = FetchType.EAGER)
    private Set<DirectorEntity> directorEntities;

    @OneToOne(mappedBy = "movieEntity")
    private RatingEntity ratings;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public Set<StarEntity> getStars() {
        return starEntities;
    }

    public Set<DirectorEntity> getDirectors() {
        return directorEntities;
    }

    public RatingEntity getRatings() {
        return ratings;
    }

}
