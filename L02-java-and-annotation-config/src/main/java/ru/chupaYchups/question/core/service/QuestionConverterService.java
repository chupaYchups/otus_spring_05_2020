package ru.chupaYchups.question.core.service;

import ru.chupaYchups.question.Question;

public interface QuestionConverterService {

    Question covertToQuestion(String questionString, String answerString);
}
