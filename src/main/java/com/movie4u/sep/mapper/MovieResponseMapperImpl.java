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
        if(movieEntity == null) return null;
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
        if (movie.getRatings() == null) return null;
        Rating rating = new Rating();
        var ratingEntity = movie.getRatings();
        rating.setMovieId(ratingEntity.getMovieId());
        rating.setRating(ratingEntity.getRating());
        rating.setVotes(ratingEntity.getVotes());

        return rating;
    }


    private List<People> mapDirectors(MovieEntity movie) {
       List<People> peopleList = new ArrayList<>();
        if (movie.getDirectors() == null) return null;

       for (var e: movie.getDirectors()){
           if (e != null && e.getDirector() != null){
           People p = new People();
           p.setId(e.getDirector().getId());
           p.setName(e.getDirector().getName());
           p.setBirth(e.getDirector().getBirth());
           peopleList.add(p);
       }}

       return peopleList.isEmpty() ? null : peopleList;
    }


    private List<People> mapStars(MovieEntity movie) {
        List<People> peopleList = new ArrayList<>();
        if (movie.getStars() == null) return null;

        for (var s: movie.getStars()) {
            if (s != null && s.getStar() != null) {
                People p = new People();
                p.setId(s.getStar().getId());
                p.setName(s.getStar().getName());
                p.setBirth(s.getStar().getBirth());
                peopleList.add(p);
            }
        }

        return peopleList.isEmpty() ? null : peopleList;
    }

}
