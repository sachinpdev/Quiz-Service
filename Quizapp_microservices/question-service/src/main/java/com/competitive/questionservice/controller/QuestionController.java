package com.competitive.questionservice.controller;


import com.competitive.questionservice.model.Question;
import com.competitive.questionservice.model.QuestionWrapper;
import com.competitive.questionservice.model.Response;
import com.competitive.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("allQuestions")
    public List<Question> get(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getAllQuestionsByCategory(category);
    }

    @PostMapping("add")
    public String addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PutMapping("update/{category}")
    public String updateQuestion(@RequestBody Question question, @PathVariable String category){
        return questionService.updateQuestionByCategory(category, question);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam int numQ){
        return questionService.getQuestionForQuiz(category, numQ);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> ids){
        // Just to check Load balancer is working or not
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsForIds(ids);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
}
