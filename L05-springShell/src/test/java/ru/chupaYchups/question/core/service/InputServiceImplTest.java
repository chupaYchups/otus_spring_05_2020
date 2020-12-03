package ru.chupaYchups.question.core.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.chupaYchups.question.service.InputService;
import ru.chupaYchups.question.service.InputServiceImpl;

import java.io.ByteArrayInputStream;

@DisplayName("Тестирование того что")
class InputServiceImplTest {

    private final String WHITE_SPACE = "\n";
    private final String TEST_STRING_PART1 = "my";
    private final String TEST_STRING_PART2 = "test";
    private final String TEST_STRING_PART3 = "string";
    private final String TEST_STRING = TEST_STRING_PART1 + WHITE_SPACE +
            TEST_STRING_PART2 +  WHITE_SPACE + TEST_STRING_PART3;

    private InputService inputService;

    @BeforeEach
    void setUp() {
        inputService = new InputServiceImpl(new ByteArrayInputStream(TEST_STRING.getBytes()));
    }
    @Test
    @DisplayName("сервис корректно возвращает строки из входящего потока")
    public void testThatInputServiceCorrectReturnLinesofInput() {
        Assertions.assertThat(inputService.getInput()).isEqualTo(TEST_STRING_PART1);
        Assertions.assertThat(inputService.getInput()).isEqualTo(TEST_STRING_PART2);
        Assertions.assertThat(inputService.getInput()).isEqualTo(TEST_STRING_PART3);
        Assertions.assertThat(inputService.getInput()).isNull();
    }
}