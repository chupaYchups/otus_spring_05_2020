package ru.chupaYchups.question.core.dao;

import ru.chupaYchups.question.core.model.Question;

public interface QuestionsDao {

    boolean hasNext();

    Question next();
}
