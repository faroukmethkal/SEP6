package com.movie4u.sep.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;


@SpringBootTest
class MovieRepositoryIT {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private StarRepository starRepository;
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private PeopleRepository peopleRepository;

    @Test
    void find_directory() {

        System.out.printf("Directory size: " + directorRepository.findByPersonIdAndMovieId(529960, 15724));

    }

    @Test
    void find_movie() {
        System.out.printf("Movie size: " + movieRepository.findByTitleContainingIgnoreCase("Wen", PageRequest.of(0, 2)));
    }

    @Test
    void find_star() {
        System.out.printf("Star size: " + starRepository.findById(15414));
    }

    @Test
    void find_ratings() {
        System.out.printf("Rating size: " + ratingRepository.findAll().size());
    }

    @Test
    void find_people() {

        System.out.printf("People size: " + peopleRepository.findAll().size());
    }

}