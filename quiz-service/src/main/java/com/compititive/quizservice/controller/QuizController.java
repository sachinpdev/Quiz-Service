package com.compititive.quizservice.controller;

import com.compititive.quizservice.dto.QuizDTO;
import com.compititive.quizservice.model.QuestionWrapper;
import com.compititive.quizservice.model.Response;
import com.compititive.quizservice.service.QuizService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody @NonNull QuizDTO quizDTO){
        return quizService.createQuiz(quizDTO.getCategoryName(), quizDTO.getNumOfQuestions(), quizDTO.getTitle());
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }

}
