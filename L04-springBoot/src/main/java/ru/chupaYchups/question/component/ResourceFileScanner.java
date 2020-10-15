package ru.chupaYchups.question.component;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ResourceFileScanner {

    private final QuestionsFileNameResolver questionsFileNameResolver;
    private Scanner scanner;

    public ResourceFileScanner(QuestionsFileNameResolver questionsFileNameResolver) {
        this.questionsFileNameResolver = questionsFileNameResolver;
    }

    public Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(getClass().getClassLoader().getResourceAsStream(questionsFileNameResolver.getFileName()));
        }
        return scanner;
    }
}
