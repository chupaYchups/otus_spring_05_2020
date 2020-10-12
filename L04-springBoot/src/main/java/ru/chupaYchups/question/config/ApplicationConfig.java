package ru.chupaYchups.question.config;

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
    public QuestionsDao questionsDaoCsv(StringToQuestionConverter stringToQuestionConverter, QuestionsFileNameResolver questionsFileNameResolver) {
        return new QuestionsDaoCsv(new ResourceFileScanner(questionsFileNameResolver.getFileName()), stringToQuestionConverter);
    }
}
