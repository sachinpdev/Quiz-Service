package com.competitive.quizapp.service;

import com.competitive.quizapp.dao.QuestionDao;
import com.competitive.quizapp.dao.QuizDao;
import com.competitive.quizapp.model.Question;
import com.competitive.quizapp.model.QuestionWrapper;
import com.competitive.quizapp.model.Quiz;
import com.competitive.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizDao.save(quiz);
        return new ResponseEntity<>("Successfully generated", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {

        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();

        questionsFromDB.forEach(q -> questionForUser.add(new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(),
                q.getOption3(), q.getOption4())));


        return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();

        int i = 0;
        int count = 0;
        for(Response response: responses){
            // Added size comparison in case if you pass more responses that the questions in the DB
            if (i < questions.size() && response.getResponse().equals(questions.get(i).getRightAnswer()))
                count++;
            i++;
        }
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
