package ru.chupaYchups.question.service;

import java.io.InputStream;
import java.util.Scanner;

public class InputServiceImpl implements InputService {

    private final InputStream inputStream;
    private Scanner scanner;

    public InputServiceImpl(InputStream inputStream)  {
        this.inputStream = inputStream;
    }

    @Override
    public String getInput() {
        if (scanner == null) {
            scanner = new Scanner(inputStream);
        }
        if(scanner.hasNext()) {
            return scanner.next();
        }
        return null;
    }
}
