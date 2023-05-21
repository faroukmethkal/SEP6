package com.movie4u.sep.db;

import com.movie4u.sep.db.entity.TopListMovie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopListMovieRepository extends JpaRepository<TopListMovie, Long> {
    Optional<List<TopListMovie>> findAllByUserId(int userId);
}
