package ru.chupaYchups.question.component;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import java.util.Locale;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Тестирование что")
class QuestionsFileNameResolverTest {

    @Configuration
    @ComponentScan(value = {"ru.chupaYchups.question.component", "ru.chupaYchups.question.config"})
    static class TestConfiguration {}

    @MockBean
    private MessageSource messageSource;

    @Autowired
    private QuestionsFileNameResolver resolver;

    private final static String QUESTION_FILE_NAME_RU = "вопросы";
    private final static String QUESTION_FILE_NAME_PROPERTY = "questions";
    private final static String QUESTION_FILE_EXT = "csv";
    private static final String LOCALE_RU = "ru";

    @Test
    @DisplayName("имя файла корректно локализуется и возвращается")
    void testThatReturnCorrectlyLocalizedFileName() {

        BDDMockito.given(messageSource.getMessage(QUESTION_FILE_NAME_PROPERTY,
            new Object[]{},
            new Locale(LOCALE_RU))).
            willReturn(QUESTION_FILE_NAME_RU);

        assertThat(resolver.getFileName()).isEqualTo(String.format("%s.%s", QUESTION_FILE_NAME_RU, QUESTION_FILE_EXT));
    }
}