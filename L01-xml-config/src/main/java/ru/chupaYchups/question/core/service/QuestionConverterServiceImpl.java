package ru.chupaYchups.question.core.service;

import ru.chupaYchups.question.Question;

public class QuestionConverterServiceImpl implements QuestionConverterService {

    @Override
    public Question covertToQuestion(String questionString, String answerString) {
        return new Question(questionString, answerString);
    }
}
