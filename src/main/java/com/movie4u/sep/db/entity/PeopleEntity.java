package com.movie4u.sep.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "people")
public class PeopleEntity {
    @Id
    private int id;
    private String name;
    private Long birth;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getBirth() {
        return birth;
    }

}
