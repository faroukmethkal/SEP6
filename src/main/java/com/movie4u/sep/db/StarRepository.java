package com.movie4u.sep.db;

import com.movie4u.sep.db.entity.MovieEntity;
import com.movie4u.sep.db.entity.PeopleEntity;
import com.movie4u.sep.db.entity.StarEntity;
import com.movie4u.sep.db.entity.StarMovieKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StarRepository extends JpaRepository<StarEntity, StarMovieKey> {
    Optional<List<StarEntity>> findAllByStar(PeopleEntity entity);
}
