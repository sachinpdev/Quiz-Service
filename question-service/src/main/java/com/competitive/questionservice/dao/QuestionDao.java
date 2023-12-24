package com.competitive.questionservice.dao;

import com.competitive.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT q.id FROM question q where q.category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Integer> findRandomQuestionByCategory(String category, int numQ);

    @Query(value = "SELECT q.id FROM question q where q.id IN (:=ids)", nativeQuery = true)
    List<Question> findByIds(List<Integer> ids);
}
