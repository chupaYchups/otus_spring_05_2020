package ru.chupaYchups.question.core.service;

import org.springframework.stereotype.Component;
import ru.chupaYchups.question.core.model.Question;

@Component
public class QuestionConverterServiceImpl implements QuestionConverterService {
    @Override
    public Question covertToQuestion(String questionString, String answerString) {
        return new Question(questionString, answerString);
    }
}
