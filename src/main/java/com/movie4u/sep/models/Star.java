package com.movie4u.sep.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stars")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Star implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;

    private int personId;
}


