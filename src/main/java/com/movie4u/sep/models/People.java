package com.movie4u.sep.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "people")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class People {
    @Id
    private int id;
    private String name;
    private Long birth;
}
