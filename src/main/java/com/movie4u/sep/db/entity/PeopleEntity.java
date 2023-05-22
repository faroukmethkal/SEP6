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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirth(Long birth) {
        this.birth = birth;
    }
}
