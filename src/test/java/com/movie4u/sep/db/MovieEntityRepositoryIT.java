package com.movie4u.sep.db;

import com.movie4u.sep.service.mapper.MovieResponseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class MovieEntityRepositoryIT {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieResponseMapper mapper;

   @Test
    void find_movie_mapper() {
       var optionalMovies = movieRepository.findByTitleContainingIgnoreCase("El", PageRequest.of(0, 10));

       var mapped =  optionalMovies.orElseThrow()
               .stream().map(m -> mapper.map(m))
               .toList();
        for (var r :mapped) {
            System.out.println(r);
        }
    }

}

