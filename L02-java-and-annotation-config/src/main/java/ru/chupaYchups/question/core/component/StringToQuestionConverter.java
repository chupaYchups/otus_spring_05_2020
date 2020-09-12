package ru.chupaYchups.question.core.component;

import org.springframework.stereotype.Component;
import ru.chupaYchups.question.core.model.Question;

@Component
public interface StringToQuestionConverter {
    Question covertToQuestion(String oneLineString);
}
