package com.movie4u.sep.service;


import com.movie4u.sep.db.MovieRepository;
import com.movie4u.sep.db.PeopleRepository;

import com.movie4u.sep.db.TopListMovieRepository;
import com.movie4u.sep.db.entity.TopListMovie;
import com.movie4u.sep.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private  PeopleRepository peopleRepository;

    private  MovieRepository movieRepository;
    private  TopListMovieRepository topListMovieRepository;

    @Autowired
    public UserService(PeopleRepository peopleRepository,
                       MovieRepository movieRepository,
                      TopListMovieRepository topListMovieRepository) {
        this.peopleRepository = peopleRepository;
        this.movieRepository = movieRepository;
        this.topListMovieRepository = topListMovieRepository;
    }

    public boolean login(String username, int id){
       var user = peopleRepository.findById(id);

       return user.filter(peopleEntity -> (username.contains(peopleEntity.getName()))).isPresent();

   }

   public ResponseEntity<String> addToTopList(Integer movieId, Integer userId) {
        boolean userExist = peopleRepository.existsById(userId);
        boolean movieExist = movieRepository.existsById(movieId);
        if (userExist && movieExist) {
            TopListMovie topListMovie = new TopListMovie();
            topListMovie.setMovieId(movieId);
            topListMovie.setPeopleId(userId);
            try {
                topListMovieRepository.save(topListMovie);
            } catch (Exception e) {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate key the value already saved");
            }
            return ResponseEntity.ok("Saved");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Either movie id or user id not exists");
        }
   }

   public ResponseEntity<List<Movie>> getTopListMovie (int userId) {
        var allMovie = topListMovieRepository.findAllByUserId(userId);
        return null;
   }
}
