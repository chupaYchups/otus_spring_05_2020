package ru.chupaYchups.question.output;

import ru.chupaYchups.question.core.service.QuestionInputService;

import java.io.InputStream;
import java.util.Scanner;

public class QuestionInputServiceImpl implements QuestionInputService {

    private Scanner scanner;

    public QuestionInputServiceImpl(InputStream inputStream)  {
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
