package ru.chupaYchups.question.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.chupaYchups.question.config.ApplicationProps;
import ru.chupaYchups.question.config.QuestionsFileProps;

import java.util.Locale;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тестирование что")
class QuestionsFileNameResolverTest {

    @Mock
    private MessageSource messageSource;

    private QuestionsFileNameResolver resolver;
    private QuestionsFileProps questionsFileProps;
    private ApplicationProps applicationProps;

    private final static String QUESTION_FILE_NAME_RU = "вопросы";
    private final static String QUESTION_FILE_NAME_PROPERTY = "questions";
    private final static String QUESTION_FILE_EXT = "csv";
    private static final String LOCALE_RU = "ru";

    @BeforeEach
    void setUp() {
        questionsFileProps = new QuestionsFileProps();
        questionsFileProps.setNameProperty(QUESTION_FILE_NAME_PROPERTY);
        questionsFileProps.setExtension(QUESTION_FILE_EXT);

        applicationProps = new ApplicationProps();
        applicationProps.setLocale(new Locale(LOCALE_RU));

        resolver = new QuestionsFileNameResolver(messageSource, questionsFileProps,  applicationProps);
    }

    @Test
    @DisplayName("имя файла корректно локализуется и возвращается")
    void testThatReturnCorrectlyLocalizedFileName() {
        BDDMockito.given(messageSource.getMessage(questionsFileProps.getNameProperty(), new Object[]{}, applicationProps.getLocale())).
            willReturn(QUESTION_FILE_NAME_RU);

        assertThat(resolver.getFileName()).isEqualTo(String.format("%s.%s", QUESTION_FILE_NAME_RU, QUESTION_FILE_EXT));
    }
}