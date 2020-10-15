package ru.chupaYchups.question.dao;

import org.springframework.stereotype.Repository;
import ru.chupaYchups.question.component.ResourceFileScanner;
import ru.chupaYchups.question.component.StringToQuestionConverter;
import ru.chupaYchups.question.model.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Repository
public class QuestionsDaoCsv implements QuestionsDao {

    private final ResourceFileScanner resourceFileScanner;
    private final StringToQuestionConverter converter;

    public QuestionsDaoCsv(ResourceFileScanner resourceFileScanner, StringToQuestionConverter converter) {
        this.resourceFileScanner = resourceFileScanner;
        this.converter = converter;
    }

    @Override
    public List<Question> findAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        Scanner scanner = resourceFileScanner.getScanner();
        while(scanner.hasNextLine()){
            questionList.add(converter.convertToQuestion(scanner.nextLine()));
        }
        return questionList;
    }
}
