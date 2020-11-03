package ru.chupaYchups.question.core.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@PropertySource("classpath:application.properties")
public class ResourceFileScanner {

    private Scanner scanner;

    public ResourceFileScanner(@Value("${questions.file.name}") String resourceFileName) {
        this.scanner = new Scanner(getClass().getClassLoader().getResourceAsStream(resourceFileName));
    }

    public Scanner getScanner() {
        return scanner;
    }
}
