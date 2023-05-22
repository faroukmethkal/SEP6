package com.movie4u.sep.db;

import com.movie4u.sep.db.entity.PeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<PeopleEntity, Integer> {
    List<PeopleEntity> findByNameIgnoreCaseContaining(String name);
}
