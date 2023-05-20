package com.movie4u.sep.mapper;

import com.movie4u.sep.db.entity.MovieEntity;
import com.movie4u.sep.models.Movie;
import org.springframework.stereotype.Component;

@Component
public interface MovieResponseMapper {
    Movie map(MovieEntity movieEntity);
}
