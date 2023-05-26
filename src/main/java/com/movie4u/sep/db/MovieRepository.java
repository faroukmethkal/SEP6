package com.movie4u.sep.db;


import com.movie4u.sep.db.entity.MovieEntity;
import com.movie4u.sep.db.entity.PeopleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

    Optional<List<MovieEntity>> findByTitleContainingIgnoreCase(String title, Pageable pageRequest);
    Optional<List<MovieEntity>> findByStarEntities(PeopleEntity star);

    @Query("SELECT r.rating, COUNT(r.rating), AVG(r.rating) FROM MovieEntity m JOIN m.ratings r WHERE m.year >= :startYear AND m.year < :endYear GROUP BY r.rating")
    List<Object[]> findRatingStatisticsInDecade(Long startYear, Long endYear);


}
