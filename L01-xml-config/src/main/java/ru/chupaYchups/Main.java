package ru.chupaYchups;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.chupaYchups.question.core.dao.QuestionsDao;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionsDao dao = context.getBean(QuestionsDao.class);
        while (dao.hasNext()){
            System.out.println(dao.next().getQuestionString());
        }
    }
}