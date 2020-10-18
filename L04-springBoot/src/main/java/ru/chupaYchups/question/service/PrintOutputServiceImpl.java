package ru.chupaYchups.question.service;

import java.io.PrintStream;

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
