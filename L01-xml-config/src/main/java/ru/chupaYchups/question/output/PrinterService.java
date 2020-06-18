package ru.chupaYchups.question.output;

import ru.chupaYchups.question.Question;
import ru.chupaYchups.question.core.service.QuestionPrinterService;
import java.io.PrintStream;

public class PrinterService implements QuestionPrinterService {

    private PrintStream printStream;

    public PrinterService(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void printQuestion(Question question) {
        printStream.println(question.getQuestionString());
    }
}
