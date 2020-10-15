package ru.chupaYchups.question.model;

import java.util.Iterator;
import java.util.List;

public class TestingAttempt {

    private final int qtyToSuccess;
    private final int qtyOfQuestions;

    private final Iterator<Question> questionIterator;
    private final Student student;

    private int successCounter;

    public TestingAttempt(List<Question> questionList, Student student, int qtyToSuccess) {
        this.successCounter = 0;
        this.questionIterator = questionList.iterator();
        this.qtyOfQuestions = questionList.size();
        this.qtyToSuccess = qtyToSuccess;
        this.student = student;
    }

    public Question getNextQuestion() {
        return questionIterator.next();
    }

    public void incrementSuccessCounter() {
        successCounter++;
    }

    public boolean isContinued() {
        return questionIterator.hasNext();
    }

    public Student getStudent() {
        return student;
    }

    public String getResult() {
        return successCounter + "/" + qtyOfQuestions;
    }

    public boolean isSuccess() {
        return !isContinued() && successCounter >= qtyToSuccess;
    }
}
