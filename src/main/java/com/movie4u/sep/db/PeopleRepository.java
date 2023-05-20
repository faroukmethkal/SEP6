package com.movie4u.sep.db;

import com.movie4u.sep.db.entity.PeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<PeopleEntity, Integer> {
}
