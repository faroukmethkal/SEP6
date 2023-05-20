package com.movie4u.sep.db;

import com.movie4u.sep.db.entity.DirectorEntity;
import com.movie4u.sep.mapper.MovieResponseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;


@SpringBootTest
class MovieEntityRepositoryIT {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private MovieResponseMapper mapper;

    @Test
    void find_directory() {

        //   System.out.printf("Directory size: " +
        //   directorRepository.findByPersonIdAndMovieId(529960, 15724));

    }

    @Test
    void find_movie() {
        var optionalMovies = movieRepository.findByTitleContainingIgnoreCase("El", PageRequest.of(0, 10));

        if (optionalMovies.isPresent()) {
            var movies = optionalMovies.get();

            for (var movie : movies) {
                System.out.println("Movie ID: " + movie.getId());
                System.out.println("Title: " + movie.getTitle());
                System.out.println("Year: " + movie.getYear());

                var director = movie.getDirectors().stream().findFirst().map(DirectorEntity::getDirector).orElse(null);
                if (director != null) {
                    System.out.println("Director: " + director.getName());
                }

                var stars = movie.getStars();
                for (var star : stars) {
                    System.out.println("Star: " + star.getStar().getName());
                }

                System.out.println("------------------------");

                var rate = movie.getRatings();

                System.out.println("Rate: " + rate.getRating());
                System.out.println("Vote: " + rate.getVotes());


                System.out.println("------------------------");
            }
        } else {
            System.out.println("No movies found.");
        }
    }

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