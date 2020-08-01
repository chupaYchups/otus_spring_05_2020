package ru.chupaYchups.question.output;

import ru.chupaYchups.question.Question;
import ru.chupaYchups.question.core.service.QuestionOutputService;
import java.io.PrintStream;

public class QuestionOutputServiceImpl implements QuestionOutputService {

    public static final String TEST_SUCCESS_OUTPUT = "Test is successfully finished! Congratulations!";
    public static final String TEST_FAILED_OUTPUT = "Test is failed! Try again please!";

    private PrintStream printStream;

    public QuestionOutputServiceImpl(PrintStream printStream) {
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
