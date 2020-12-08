package ru.chupaYchups.question.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.chupaYchups.question.component.QuestionsFileNameResolver;
import ru.chupaYchups.question.component.ResourceFileScanner;
import ru.chupaYchups.question.component.StringToQuestionConverter;
import ru.chupaYchups.question.dao.QuestionsDao;
import ru.chupaYchups.question.dao.QuestionsDaoCsv;
import ru.chupaYchups.question.service.InputService;
import ru.chupaYchups.question.service.InputServiceImpl;
import ru.chupaYchups.question.service.PrintOutputService;
import ru.chupaYchups.question.service.PrintOutputServiceImpl;

import java.io.InputStream;
import java.io.PrintStream;

@Configuration
@EnableConfigurationProperties({ApplicationProps.class, TestingProps.class, QuestionsFileProps.class})
public class ApplicationConfig {

    @Bean
    public MessageSource messageSource() {
        var ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean
    public PrintOutputService printOutputService(@Value("#{T(java.lang.System).out}") PrintStream printStream) {
        return new PrintOutputServiceImpl(printStream);
    }

    @Bean
    public InputService inputService(@Value("#{T(java.lang.System).in}") InputStream inputStream) {
        return new InputServiceImpl(inputStream);
    }
}
