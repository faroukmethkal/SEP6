package com.movie4u.sep.controller;

import com.movie4u.sep.models.Movie;
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam(required = true) String username, Integer userId) {
        boolean loggedIn = userService.login(username, userId);

        return loggedIn ? ResponseEntity.ok("Login successful") :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

    }

    @PostMapping("/topList")
    public ResponseEntity<String> addToTopList(@RequestParam(required = true) Integer userId, Integer movieId) {
        return userService.addToTopList(movieId, userId);

    }

    @GetMapping("/topList")
    public ResponseEntity<List<Movie>> getTopList(@RequestParam(required = true) Integer userId) {
        return  ResponseEntity.ok(userService.getTopListMovie(userId));

    }

}
