package ru.chupaYchups.question.csv.component;

import org.springframework.stereotype.Component;
import ru.chupaYchups.question.core.component.StringToQuestionConverter;
import ru.chupaYchups.question.core.model.Question;

@Component
public class StringToQuestionConverterCsv implements StringToQuestionConverter {
    @Override
    public Question covertToQuestion(String oneLineString) {
        String[] questionAndAnswer = oneLineString.split(";");
        return new Question(questionAndAnswer[0], questionAndAnswer[1]);
    }
}
