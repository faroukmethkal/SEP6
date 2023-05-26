package com.movie4u.sep.service;

import com.movie4u.sep.db.MovieRepository;
import com.movie4u.sep.db.TopListMovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    TopListMovieRepository topListMovieRepository;
    @Autowired
    MovieRepository movieRepository;

    @Test
    void save() {
       /* TopListMovie topListMovie = new TopListMovie();
        topListMovie.setMovieId(35423);
        topListMovie.setPeopleId(12448 );
        topListMovieRepository.save(topListMovie);*/
    }

  /*  @Test
    void findAllByUserId() {
        var topList = topListMovieRepository.findAllByUsername(12448);
        List<Integer> list = new ArrayList<>();
        for (var t: topList.get()) {
            list.add(t.getMovieId());
        }
        System.out.println(movieRepository.findAllById(list).size());
    }*/

}