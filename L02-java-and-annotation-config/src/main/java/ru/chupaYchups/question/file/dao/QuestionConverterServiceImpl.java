package ru.chupaYchups.question.file.dao;

import org.springframework.stereotype.Component;
import ru.chupaYchups.question.core.model.Question;

@Component
public class QuestionConverterServiceImpl implements QuestionConverterService {
    @Override
    public Question covertToQuestion(String oneLineString) {
        String[] questionAndAnswer = oneLineString.split(";");
        return new Question(questionAndAnswer[0], questionAndAnswer[1]);
    }
}
