package com.movie4u.sep.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Movie {

    @Id
    private int id;
    private String title;
    private int year;
}
