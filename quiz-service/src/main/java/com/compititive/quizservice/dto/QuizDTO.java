package com.compititive.quizservice.dto;

import lombok.Data;

@Data
public class QuizDTO {
    String categoryName;
    Integer numOfQuestions;
    String title;
}
