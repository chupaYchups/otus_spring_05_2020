package ru.chupaYchups;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.chupaYchups.question.core.service.QuestionManagerService;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionManagerService managerService = context.getBean(QuestionManagerService.class);
        managerService.printAllQuestions();
    }
}