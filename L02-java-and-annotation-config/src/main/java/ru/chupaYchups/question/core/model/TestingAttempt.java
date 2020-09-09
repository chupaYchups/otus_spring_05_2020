package ru.chupaYchups.question.core.model;

import java.util.Iterator;
import java.util.List;

public class TestingAttempt {

    private final String USER_NAME;
    private final int QTY_TO_SUCCESS;
    private final int QUESTIONS_QTY;
    private final Iterator<Question> QUESTIONS_ITERATOR;

    private int sucessCounter;

    public TestingAttempt(List<Question> questionList, String username, int qtyToSuccess) {
        this.sucessCounter = 0;
        this.QUESTIONS_ITERATOR = questionList.iterator();
        this.QUESTIONS_QTY = questionList.size();
        this.QTY_TO_SUCCESS = qtyToSuccess;
        this.USER_NAME = username;
    }
    public Question getNextQuestion() {
        return QUESTIONS_ITERATOR.next();
    }
    public void incrementSuccessCounter() {
        sucessCounter++;
    }
    public boolean isContinued() {
        return QUESTIONS_ITERATOR.hasNext() && sucessCounter < QTY_TO_SUCCESS;
    }
    public String getUserName() {
        return USER_NAME;
    }
    public String getResult() {
        return sucessCounter + "/" + QUESTIONS_QTY;
    }
}
