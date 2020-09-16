package ru.chupaYchups.question.core.component;

import ru.chupaYchups.question.core.model.Question;

public interface StringToQuestionConverter {
    Question covertToQuestion(String oneLineString);
}
