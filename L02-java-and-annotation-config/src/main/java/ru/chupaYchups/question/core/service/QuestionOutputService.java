package ru.chupaYchups.question.core.service;

import ru.chupaYchups.question.Question;

public interface QuestionOutputService {
    void printQuestion(Question question);
    void printTestResult(boolean isTestOk);
}
