package ru.chupaYchups.question;

public class Question {

    private final String questionString;
    private final String answerString;
    private final String QUESTION_MARK = "?";

    public Question(String questionString, String answerString) {
        this.questionString = questionString;
        this.answerString = answerString;
    }

    public boolean checkAnswer(String answer) {
        return answer != null && answer.trim().equalsIgnoreCase(answerString);
    }

    public String getQuestionString() {
        return questionString + QUESTION_MARK;
    }
    public String getAnswerString() {
        return answerString;
    }
}
