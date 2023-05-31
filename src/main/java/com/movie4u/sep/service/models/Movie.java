package com.movie4u.sep.service.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@JsonSerialize
@Data
@ToString
public class Movie {

    private int id;
    private String title;
    private Long year;

    private Rating rating;

    List<People> directors;

    List<People> stars;

}
