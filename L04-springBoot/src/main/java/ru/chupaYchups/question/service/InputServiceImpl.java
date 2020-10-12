package ru.chupaYchups.question.service;

import java.io.InputStream;
import java.util.Scanner;

public class InputServiceImpl implements InputService {

    private final Scanner scanner;

    public InputServiceImpl(InputStream inputStream)  {
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public String getInput() {
        if(scanner.hasNext()) {
            return scanner.next();
        }
        return null;
    }
}
