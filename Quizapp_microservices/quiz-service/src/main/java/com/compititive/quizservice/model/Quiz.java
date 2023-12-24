package com.compititive.quizservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    // when you have entity type then use ORM relations or else collect them
    @ElementCollection
    private List<Integer> questions;
}
