package com.movie4u.sep.service;

import com.movie4u.sep.db.TopListMovieRepository;
import com.movie4u.sep.db.entity.TopListMovie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    TopListMovieRepository topListMovieRepository;

    @Test
    void save() {
       /* TopListMovie topListMovie = new TopListMovie();
        topListMovie.setMovieId(35423);
        topListMovie.setPeopleId(12448 );
        topListMovieRepository.save(topListMovie);*/
    }

}