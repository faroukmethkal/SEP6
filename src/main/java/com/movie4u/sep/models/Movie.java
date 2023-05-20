package com.movie4u.sep.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;


@JsonSerialize
@Data
public class Movie {

    private int id;
    private String title;
    private int year;

    private Rating rating;

    List<People> directors;

    List<People> stars;

}
