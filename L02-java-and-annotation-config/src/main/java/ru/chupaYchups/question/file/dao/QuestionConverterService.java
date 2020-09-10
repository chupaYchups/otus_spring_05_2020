package ru.chupaYchups.question.file.dao;

import org.springframework.stereotype.Component;
import ru.chupaYchups.question.core.model.Question;

@Component
public interface QuestionConverterService {
    Question covertToQuestion(String oneLineString);
}
