package ru.chupaYchups.question.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Scanner;

@Service
public class QuestionInputServiceImpl implements QuestionInputService {

    private Scanner scanner;

    public QuestionInputServiceImpl(@Value("#{T(java.lang.System).in}")InputStream inputStream)  {
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
