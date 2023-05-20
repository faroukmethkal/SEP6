package com.movie4u.sep.db;

import com.movie4u.sep.db.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<RatingEntity, Integer> {
}
