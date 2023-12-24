package com.competitive.quizapp.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonPropertyOrder({ "id", "questionTitle", "option1", "option2", "option3", "option4" })
public class QuestionWrapper {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
