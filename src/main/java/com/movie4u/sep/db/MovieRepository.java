package com.movie4u.sep.db;


import com.movie4u.sep.db.entity.MovieEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

    Optional<List<MovieEntity>> findByTitleContainingIgnoreCase(String title, Pageable pageRequest);
}
