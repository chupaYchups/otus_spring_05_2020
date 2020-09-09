package ru.chupaYchups.question.core.dao;

import ru.chupaYchups.question.core.model.Question;

import java.util.List;

public interface QuestionsDao {

    List<Question> findAllQuestion();
}
