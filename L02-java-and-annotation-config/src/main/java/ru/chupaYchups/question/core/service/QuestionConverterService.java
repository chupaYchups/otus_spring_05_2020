package ru.chupaYchups.question.core.service;


import ru.chupaYchups.question.core.model.Question;

public interface QuestionConverterService {
    Question covertToQuestion(String questionString, String answerString);
}
