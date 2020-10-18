package ru.chupaYchups.question.component;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.chupaYchups.question.config.ApplicationProps;
import ru.chupaYchups.question.config.QuestionsFileProps;

@Component
public class QuestionsFileNameResolver {

    private final MessageSource messageSource;
    private final QuestionsFileProps questionsFileProps;
    private final ApplicationProps applicationProps;

    public QuestionsFileNameResolver(MessageSource messageSource, QuestionsFileProps questionsFileProps, ApplicationProps applicationProps) {
        this.messageSource = messageSource;
        this.questionsFileProps = questionsFileProps;
        this.applicationProps = applicationProps;
    }

    public String getFileName() {
        return String.format("%s.%s",
            messageSource.getMessage(questionsFileProps.getNameProperty(), new Object[] {}, applicationProps.getLocale()),
            questionsFileProps.getExtension());
    }
}
