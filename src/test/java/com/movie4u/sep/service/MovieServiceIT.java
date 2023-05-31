package com.movie4u.sep.service;

import com.movie4u.sep.db.MovieRepository;
import com.movie4u.sep.db.PeopleRepository;
import com.movie4u.sep.db.StarRepository;
import com.movie4u.sep.service.models.RatingStatistics;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MovieServiceIT {
    @Autowired
    private MovieRepository movieRepository;

   @Test
    void findAllStatisticInDecade() {
       // Arrange, Act
        var results = movieRepository.findRatingStatisticsInDecade(2000L, 2010L);
        List<RatingStatistics> statistics = new ArrayList<>();
        for (var result : results) {
            float rating = (float) result[0];
            long count = (long) result[1];
            RatingStatistics ratingStatistics = new RatingStatistics(rating, count);
            statistics.add(ratingStatistics);
        }

        // Assert
       Assertions.assertThat(statistics).isNotNull();
    }
}