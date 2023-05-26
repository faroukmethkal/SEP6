package com.movie4u.sep.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RatingStatistics {

    private float rating;
    private long count;
}
