package ru.chupaYchups.question.core.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Scanner;

@Service
public class InputServiceImpl implements InputService {

    private Scanner scanner;

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
