package ru.chupaYchups.question.csv.component;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.chupaYchups.question.core.component.StringToQuestionConverter;
import ru.chupaYchups.question.core.config.ApplicationProperties;
import ru.chupaYchups.question.core.model.Question;

@Component
public class StringToQuestionConverterCsv implements StringToQuestionConverter {

    private MessageSource messageSource;
    private ApplicationProperties appProperties;

    public StringToQuestionConverterCsv(MessageSource messageSource, ApplicationProperties appProperties) {
        this.messageSource = messageSource;
        this.appProperties = appProperties;
    }

    @Override
    public Question covertToQuestion(String oneLineString) {
        String[] questionAndAnswer = oneLineString.split(";");
        return new Question(messageSource.getMessage(questionAndAnswer[0], null, appProperties.getLocale()),
            messageSource.getMessage(questionAndAnswer[1], null, appProperties.getLocale()));
    }
}
