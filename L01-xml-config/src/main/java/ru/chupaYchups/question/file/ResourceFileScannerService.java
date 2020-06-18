package ru.chupaYchups.question.file;

import java.util.Scanner;

public class ResourceFileScannerService {

    private Scanner scanner;

    public ResourceFileScannerService(String resourceFileName) {
        this.scanner = new Scanner(getClass().getClassLoader().getResourceAsStream(resourceFileName));
    }

    public Scanner getScanner() {
        return scanner;
    }
}
