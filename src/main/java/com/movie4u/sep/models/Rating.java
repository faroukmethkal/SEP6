package com.movie4u.sep.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;
    private float rating;
    private int votes;
}
