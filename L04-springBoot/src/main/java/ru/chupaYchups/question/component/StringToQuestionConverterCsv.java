package ru.chupaYchups.question.component;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.chupaYchups.question.config.ApplicationProps;
import ru.chupaYchups.question.model.Question;

@Component
public class StringToQuestionConverterCsv implements StringToQuestionConverter {

    private ApplicationProps appProperties;

    public StringToQuestionConverterCsv(ApplicationProps appProperties) {
        this.appProperties = appProperties;
    }

    @Override
    public Question covertToQuestion(String oneLineString) {
        String[] questionAndAnswer = oneLineString.split(";");
        return new Question(questionAndAnswer[0], questionAndAnswer[1]);
    }
}
