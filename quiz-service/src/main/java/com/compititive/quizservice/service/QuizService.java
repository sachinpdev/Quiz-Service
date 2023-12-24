package com.compititive.quizservice.service;

import com.compititive.quizservice.dao.QuizDao;
import com.compititive.quizservice.feign.QuizInterface;
import com.compititive.quizservice.model.QuestionWrapper;
import com.compititive.quizservice.model.Quiz;
import com.compititive.quizservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        // You can use rest template to get the QuesionIds But lets try out Feign

        List<Integer> questionIds = quizInterface.generateQuestions(category, numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionIds);
        quizDao.save(quiz);
        return new ResponseEntity<>("Successfully generated", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Integer> questionIds = quiz.get().getQuestions();

       return quizInterface.getQuestionsFromId(questionIds);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        return quizInterface.getScore(responses);
    }
}
