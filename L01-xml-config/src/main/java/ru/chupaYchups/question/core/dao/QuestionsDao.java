package ru.chupaYchups.question.core.dao;

import ru.chupaYchups.question.Question;

public interface QuestionsDao {

    boolean hasNext();

    Question next();
}
