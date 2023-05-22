package com.movie4u.sep.controller;

import com.movie4u.sep.models.Movie;
import com.movie4u.sep.models.People;
import com.movie4u.sep.models.Rating;
import com.movie4u.sep.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> findMovieByTitle(@RequestParam(required = true) String title) {

              return ResponseEntity.ok(movieService.findMovieByTitle(title));
    }

    @PostMapping("/ratings/star")
    public ResponseEntity<List<Rating>> getAllRatingByStar(@RequestBody People star) {
       return movieService.getAllRatingByStar(star);
    }

    @GetMapping("/ratings/star")
    public ResponseEntity<List<Rating>> getAllRatingByStarName(@RequestParam(required = true) String name) {
        return movieService.getAllRatingByStarName(name);
    }
}
