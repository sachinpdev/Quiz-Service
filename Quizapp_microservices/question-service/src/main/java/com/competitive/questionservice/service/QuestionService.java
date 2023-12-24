package com.competitive.questionservice.service;

import com.competitive.questionservice.dao.QuestionDao;
import com.competitive.questionservice.model.Question;
import com.competitive.questionservice.model.QuestionWrapper;
import com.competitive.questionservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, int numQ) {
        List<Integer> questions = questionDao.findRandomQuestionByCategory(category, numQ);


        return new ResponseEntity<>(questions, HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsForIds(List<Integer> ids) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

//        List<Question> questions = questionDao.findByIds(ids);
        ids.forEach(integer -> questions.add(questionDao.findById(integer).get()));
        questions.forEach(question -> wrappers.add(new QuestionWrapper(question.getId(), question.getQuestionTitle(),
                question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4())));

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int i = 0;
        for(Response response: responses){
            Optional<Question> question = questionDao.findById(response.getId());
            // Added size comparison in case if you pass more responses that the questions in the DB
            if (response.getResponse().equals(question.get().getRightAnswer()))
                i++;
        }
        return new ResponseEntity<>(i, HttpStatus.OK);
    }
}
