package com.movie4u.sep.service;

import com.movie4u.sep.db.MovieRepository;
import com.movie4u.sep.db.TopListMovieRepository;
import com.movie4u.sep.db.entity.TopListMovie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserServiceIT {

    @Autowired
    TopListMovieRepository topListMovieRepository;
    @Autowired
    MovieRepository movieRepository;

    @Test
    void findAllByUserId() {
        // Arrange
        var topList = topListMovieRepository.findAllByUsername("user");
        List<Integer> list = new ArrayList<>();
        for (var t: topList.get()) {
            list.add(t.getMovieId());
        }

        // act
        var actualResult = movieRepository.findAllById(list);

        // assert
        Assertions.assertThat(actualResult).isNotNull();
    }

}