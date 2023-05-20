package com.movie4u.sep.controller;

import com.movie4u.sep.models.Movie;
import com.movie4u.sep.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> findMovieByTitle(@RequestParam(required = true) String title) {

              return ResponseEntity.ok(movieService.findMovieByTitle(title));
    }
}
