package ru.chupaYchups.question.core.component;

import org.springframework.stereotype.Component;
import ru.chupaYchups.question.core.config.TestingProperties;

import java.util.Scanner;

@Component
public class ResourceFileScanner {

    private String fileName;

    public ResourceFileScanner(TestingProperties testingProperties) {
        this.fileName = testingProperties.getFileName();
    }

    public Scanner getScanner() {
        return new Scanner(getClass().getClassLoader().getResourceAsStream(fileName));
    }
}
