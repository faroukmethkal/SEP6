package com.movie4u.sep.service;

import com.movie4u.sep.db.MovieRepository;
import com.movie4u.sep.db.PeopleRepository;
import com.movie4u.sep.db.StarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MovieServiceTest {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private StarRepository starRepository;

    @Test
    void findAllMovieByStarName() {
        var star = peopleRepository.findByNameIgnoreCaseContaining("Bruce Lee");

        var starEntities = starRepository.findAllByStar(star.get(0));


        for (var m: starEntities.get()) {
            System.out.println("Rating ------------------------------------->>>>>>>>>>> "+ m.getMovie().getRatings().getRating());
        }

    }
}