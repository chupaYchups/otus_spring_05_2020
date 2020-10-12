package ru.chupaYchups.question.component;

import java.util.Scanner;

public class ResourceFileScanner {

    private final String fileName;

    public ResourceFileScanner(String fileName) {
        this.fileName = fileName;
    }

    public Scanner getScanner() {
        return new Scanner(getClass().getClassLoader().getResourceAsStream(fileName));
    }
}
