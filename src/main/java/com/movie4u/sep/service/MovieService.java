package com.movie4u.sep.service;

import com.movie4u.sep.db.*;
import com.movie4u.sep.mapper.MovieResponseMapper;
import com.movie4u.sep.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieResponseMapper mapper;


    public List<Movie> findMovieByTitle(String title) {
        var pageable = PageRequest.of(0, 3);
        var movies = movieRepository.findByTitleContainingIgnoreCase(title, pageable);

        var mapped =  movies.orElseThrow()
                .stream().map(m -> mapper.map(m))
                .toList();

        return mapped;

    }
}
