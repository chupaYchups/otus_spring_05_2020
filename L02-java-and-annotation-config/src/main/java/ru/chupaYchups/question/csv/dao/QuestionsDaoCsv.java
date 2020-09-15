package ru.chupaYchups.question.csv.dao;

import org.springframework.stereotype.Repository;
import ru.chupaYchups.question.core.component.StringToQuestionConverter;
import ru.chupaYchups.question.core.dao.QuestionsDao;
import ru.chupaYchups.question.core.model.Question;
import ru.chupaYchups.question.core.component.ResourceFileScanner;

import java.util.*;

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
            questionList.add(converter.covertToQuestion(scanner.nextLine()));
        }
        return questionList;
    }
}
