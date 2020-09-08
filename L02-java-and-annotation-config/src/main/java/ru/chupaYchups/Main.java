package ru.chupaYchups;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.chupaYchups.question.core.service.TestManagerService;


@ComponentScan
@Configuration
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        TestManagerService managerService = context.getBean(TestManagerService.class);
        managerService.answerTheQuestions(3);
    }
}