package com.movie4u.sep.controller;

import com.movie4u.sep.models.Movie;
import com.movie4u.sep.models.People;
import com.movie4u.sep.models.Rating;
import com.movie4u.sep.models.RatingStatistics;
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

    @GetMapping("/v2/movies")
    public ResponseEntity<List<Movie>> findMovieByTitleWithSize(@RequestParam(required = true) String title, Integer size) {

        return ResponseEntity.ok(movieService.findMovieByTitle(title, size));
    }

    @GetMapping("/movie")
    public ResponseEntity<Movie> findMovieById(@RequestParam(required = true) int id) {

        return movieService.findMovieById(id);
    }

    @PostMapping("/ratings/star")
    public ResponseEntity<List<Rating>> getAllRatingByStar(@RequestBody People star) {
       return movieService.getAllRatingByStar(star);
    }

    @GetMapping("/ratings/star")
    public ResponseEntity<List<Rating>> getAllRatingByStarName(@RequestParam(required = true) String name) {
        return movieService.getAllRatingByStarName(name);
    }

    @GetMapping("/ratings/year")
    public ResponseEntity<List<RatingStatistics>> getAllRatingByStarName(@RequestParam(required = true) Long startYear, Long endYear) {

        return movieService.getAllRatingByYear(startYear, endYear);
    }

}
