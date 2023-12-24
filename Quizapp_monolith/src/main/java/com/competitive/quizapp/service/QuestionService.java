package com.competitive.quizapp.service;

import com.competitive.quizapp.dao.QuestionDao;
import com.competitive.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public ResponseEntity<List<Question>> getAllQuestionsByCategory(String category) {
        return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
    }

    public String addQuestion(Question question) {
        questionDao.save(question);
        return "upload Successful";
    }

    public String updateQuestionByCategory(String category, Question question) {
//        Fetch all questions find the category update it
        return null;
    }
}
