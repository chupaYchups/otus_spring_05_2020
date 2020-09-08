package ru.chupaYchups.question.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.chupaYchups.question.core.model.Question;
import java.io.PrintStream;

@Service
public class QuestionOutputServiceImpl implements QuestionOutputService {

    public static final String TEST_SUCCESS_OUTPUT = "Test is successfully finished! Congratulations!";
    public static final String TEST_FAILED_OUTPUT = "Test is failed! Try again please!";

    private PrintStream printStream;

    public QuestionOutputServiceImpl(@Value("#{T(java.lang.System).out}")PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void printQuestion(Question question) {
        printStream.println(question.getQuestionString());
    }

    @Override
    public void printTestResult(boolean isTestOk) {
        printStream.println(isTestOk ? TEST_SUCCESS_OUTPUT :  TEST_FAILED_OUTPUT);
    }
}
