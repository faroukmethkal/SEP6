package com.movie4u.sep.db;

import com.movie4u.sep.models.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, Integer> {
}
