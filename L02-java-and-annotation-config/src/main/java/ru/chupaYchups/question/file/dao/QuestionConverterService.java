package ru.chupaYchups.question.file.dao;


import ru.chupaYchups.question.core.model.Question;

public interface QuestionConverterService {
    Question covertToQuestion(String oneLineString);
}
