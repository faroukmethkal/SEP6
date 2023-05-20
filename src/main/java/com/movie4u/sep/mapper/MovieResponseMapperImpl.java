package com.movie4u.sep.mapper;

import com.movie4u.sep.db.entity.MovieEntity;
import com.movie4u.sep.models.Movie;
import com.movie4u.sep.models.People;
import com.movie4u.sep.models.Rating;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieResponseMapperImpl implements MovieResponseMapper{
    @Override
    public Movie map(MovieEntity movieEntity) {
        Movie movie = new Movie();
        movie.setId(movieEntity.getId());
        movie.setTitle(movieEntity.getTitle());
        movie.setYear(movieEntity.getYear());
        movie.setRating(mapRating(movieEntity));
        movie.setDirectors(mapDirectors(movieEntity));
        movie.setStars(mapStars(movieEntity));
        return movie;
    }

    private Rating mapRating(MovieEntity movie) {
        Rating rating = new Rating();
        var ratingEntity = movie.getRatings();
        rating.setRating(ratingEntity.getRating());
        rating.setVotes(ratingEntity.getVotes());

        return rating;
    }


    private List<People> mapDirectors(MovieEntity movie) {
       List<People> peopleList = new ArrayList<>();

       for (var e: movie.getDirectors()){
           People p = new People();
           p.setId(e.getDirector().getId());
           p.setName(e.getDirector().getName());
           p.setBirth(e.getDirector().getBirth());
           peopleList.add(p);
       }

       return peopleList;
    }


    private List<People> mapStars(MovieEntity movie) {
        List<People> peopleList = new ArrayList<>();

        for (var e: movie.getStars()){
            People p = new People();
            p.setId(e.getStar().getId());
            p.setName(e.getStar().getName());
            p.setBirth(e.getStar().getBirth());
            peopleList.add(p);
        }

        return peopleList;
    }

}
