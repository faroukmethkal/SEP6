package com.movie4u.sep.controller;

import com.movie4u.sep.db.entity.User;
import com.movie4u.sep.service.models.Movie;
import com.movie4u.sep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody(required = true) User user) {
        return userService.register(user);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam(required = true) String username, String password) {
        boolean loggedIn = userService.login(username, password);

        return loggedIn ? ResponseEntity.ok("Login successful") :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

    }

    @PostMapping("/topList")
    public ResponseEntity<String> addToTopList(@RequestParam(required = true) String username, Integer movieId) {
        return userService.addToTopList(movieId, username);

    }

    @GetMapping("/topList")
    public ResponseEntity<List<Movie>> getTopList(@RequestParam(required = true) String username) {
        return  ResponseEntity.ok(userService.getTopListMovie(username));

    }

    @DeleteMapping("/topList")
    public ResponseEntity<String> removeFromTopList(@RequestParam(required = true) String username,  Integer movieId) {
        return userService.deleteMovie(username, movieId);

    }


}
