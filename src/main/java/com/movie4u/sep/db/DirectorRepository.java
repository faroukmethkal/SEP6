package com.movie4u.sep.db;

import com.movie4u.sep.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Integer> {
    Director findByPersonIdAndMovieId(int personId, int movieId);

}
