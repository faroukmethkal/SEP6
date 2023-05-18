package com.movie4u.sep.service;

import com.movie4u.sep.db.MovieRepository;
import com.movie4u.sep.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findMovieByTitle(String title) {
        var pageable = PageRequest.of(0, 10);
      return   movieRepository.findByTitleContainingIgnoreCase(title, pageable).get();
    }
}
