package com.movie4u.sep.service.mapper;

import com.movie4u.sep.db.entity.MovieEntity;
import com.movie4u.sep.service.models.Movie;
import org.springframework.stereotype.Component;

@Component
public interface MovieResponseMapper {
    Movie map(MovieEntity movieEntity);
}
