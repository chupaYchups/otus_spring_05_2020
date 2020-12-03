package ru.chupaYchups.question.dao;

import ru.chupaYchups.question.model.Question;

import java.util.List;

public interface QuestionsDao {
    List<Question> findAllQuestions();
}
