package ru.chupaYchups.question.component;

import ru.chupaYchups.question.model.Question;

public interface StringToQuestionConverter {
    Question convertToQuestion(String oneLineString);
}
