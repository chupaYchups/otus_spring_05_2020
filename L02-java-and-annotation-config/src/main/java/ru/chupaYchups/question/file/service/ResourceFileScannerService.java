package ru.chupaYchups.question.file.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@PropertySource("classpath:application.properties")
public class ResourceFileScannerService {

    private Scanner scanner;

    public ResourceFileScannerService(@Value("${questions.file.name}") String resourceFileName) {
        this.scanner = new Scanner(getClass().getClassLoader().getResourceAsStream(resourceFileName));
    }

    public Scanner getScanner() {
        return scanner;
    }
}
