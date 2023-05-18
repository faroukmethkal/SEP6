package com.movie4u.sep.db;

import com.movie4u.sep.models.Star;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarRepository extends JpaRepository<Star, Integer> {

}
