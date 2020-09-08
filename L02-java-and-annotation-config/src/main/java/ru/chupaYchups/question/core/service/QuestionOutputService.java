package ru.chupaYchups.question.core.service;

import ru.chupaYchups.question.core.model.Question;

public interface QuestionOutputService {
    void printQuestion(Question question);
    void printTestResult(boolean isTestOk);
}
