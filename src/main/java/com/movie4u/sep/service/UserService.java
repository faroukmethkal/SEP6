package com.movie4u.sep.service;


import com.movie4u.sep.db.MovieRepository;
import com.movie4u.sep.db.PeopleRepository;

import com.movie4u.sep.db.TopListMovieRepository;
import com.movie4u.sep.db.UserRepository;
import com.movie4u.sep.db.entity.TopListMovie;
import com.movie4u.sep.db.entity.User;
import com.movie4u.sep.mapper.MovieResponseMapper;
import com.movie4u.sep.models.Movie;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private PeopleRepository peopleRepository;

    private MovieRepository movieRepository;
    private TopListMovieRepository topListMovieRepository;
    private UserRepository userRepository;
    private MovieResponseMapper mapper;

    @Autowired
    public UserService(PeopleRepository peopleRepository,
                       MovieRepository movieRepository,
                       TopListMovieRepository topListMovieRepository, UserRepository userRepository, MovieResponseMapper mapper) {
        this.peopleRepository = peopleRepository;
        this.movieRepository = movieRepository;
        this.topListMovieRepository = topListMovieRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public boolean login(String username, String password) {
        var user = userRepository.findUserByUsername(username);

        return user.filter(user1 -> user1.getPassword().equals(password)).isPresent();

    }
    public ResponseEntity<String> register(User user) {

        try {
            userRepository.save(user);
            ResponseEntity.ok("Resister user");
        } catch (Exception  e) {
            e.printStackTrace();
           return ResponseEntity.badRequest().body("Couldn't Register check Username maybe it already exist");
        }

        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<String> addToTopList(Integer movieId, String username) {
        var userExist = userRepository.findUserByUsername(username);
        boolean movieExist = movieRepository.existsById(movieId);
        if (userExist.isPresent() && movieExist) {
            TopListMovie topListMovie = new TopListMovie();
            topListMovie.setMovieId(movieId);
            topListMovie.setUsername(username);
            try {
                topListMovieRepository.save(topListMovie);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate key the value already saved");
            }
            return ResponseEntity.ok("Saved");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Either movie id or user id not exists");
        }
    }

    public List<Movie> getTopListMovie(String username) {

        var topList = topListMovieRepository.findAllByUsername(username.trim());

        List<Integer> listId = new ArrayList<>();
        for (var t : topList.get()) {
            listId.add(t.getMovieId());
        }

        return movieRepository.findAllById(listId)
                .stream().map(m -> mapper.map(m))
                .toList();
    }
}
