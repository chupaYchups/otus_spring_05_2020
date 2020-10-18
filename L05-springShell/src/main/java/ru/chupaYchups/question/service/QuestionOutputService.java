package ru.chupaYchups.question.service;

public interface QuestionOutputService {

    void print(String msg);

    void printLocalized(String msgKey, Object... params);
}
