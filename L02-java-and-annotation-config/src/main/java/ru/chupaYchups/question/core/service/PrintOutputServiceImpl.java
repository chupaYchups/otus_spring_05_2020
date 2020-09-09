package ru.chupaYchups.question.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.chupaYchups.question.core.model.Question;
import java.io.PrintStream;

@Service
public class PrintOutputServiceImpl implements PrintOutputService {

    private final PrintStream printStream;

    public PrintOutputServiceImpl(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void print(String outputString) {
        printStream.println(outputString);
    }
}
