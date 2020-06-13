package ru.chupaYchups.question.file;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.chupaYchups.question.core.dao.QuestionsDao;
import ru.chupaYchups.question.file.ResourceFileQuestionsDao;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование того, что")
class ResourceFileQuestionsDaoTest {

    @Test
    @DisplayName("метод hasNext возвращает false если в файле нету вопросов")
    void testThatHasNextMethodReturnFalseIfHasNoQuestionsInFile() {
        QuestionsDao dao = new ResourceFileQuestionsDao("emptyTestFile.csv");
        assertThat(dao.hasNext()).isEqualTo(false);
    }

    @Test
    @DisplayName("метод next возвращает последовательно все вопросы из тестового файла")
    void testThatNextMethodCorrectlyReturnAllQuestionsFromFile() {
        QuestionsDao dao = new ResourceFileQuestionsDao("testQuestions.csv");
        assertThat(dao.hasNext()).isTrue();
        assertThat(dao.next()).hasFieldOrPropertyWithValue("questionString", "What is Vladimir Putin name?");
        assertThat(dao.next()).hasFieldOrPropertyWithValue("questionString", "What country is the biggest?");
        assertThat(dao.next()).hasFieldOrPropertyWithValue("questionString", "Sex, drugs and rock-n-roll?");
    }
}