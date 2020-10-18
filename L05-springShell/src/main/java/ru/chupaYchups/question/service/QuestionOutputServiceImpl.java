package ru.chupaYchups.question.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.chupaYchups.question.config.ApplicationProps;

@Service
public class QuestionOutputServiceImpl implements QuestionOutputService{

    private final MessageSource messageSource;
    private final PrintOutputService printOutputService;
    private final ApplicationProps applicationProps;

    public QuestionOutputServiceImpl(MessageSource messageSource, PrintOutputService printOutputService, ApplicationProps applicationProps) {
        this.messageSource = messageSource;
        this.printOutputService = printOutputService;
        this.applicationProps = applicationProps;
    }

    @Override
    public void print(String msg) {
        printOutputService.print(msg);
    }

    @Override
    public void printLocalized(String msgKey, Object... params) {
        print(messageSource.getMessage(msgKey, params, applicationProps.getLocale()));
    }
}
