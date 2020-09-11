package ru.chupaYchups.question.core.model;

import java.util.Iterator;
import java.util.List;

public class TestingAttempt {

    private final int QTY_TO_SUCCESS;
    private final int QUESTIONS_QTY;

    private final Iterator<Question> questionIterator;
    private final Student student;

    private int sucessCounter;

    public TestingAttempt(List<Question> questionList, Student student, int qtyToSuccess) {
        this.sucessCounter = 0;
        this.questionIterator = questionList.iterator();
        this.QUESTIONS_QTY = questionList.size();
        this.QTY_TO_SUCCESS = qtyToSuccess;
        this.student = student;
    }
    public Question getNextQuestion() {
        return questionIterator.next();
    }
    public void incrementSuccessCounter() {
        sucessCounter++;
    }
    public boolean isContinued() {
        return questionIterator.hasNext();
    }

    public Student getStudent() {
        return student;
    }

    public String getResult() {
        return sucessCounter + "/" + QUESTIONS_QTY;
    }

    public boolean isSuccess() {
        return !isContinued() && sucessCounter >= QTY_TO_SUCCESS;
    }
}
