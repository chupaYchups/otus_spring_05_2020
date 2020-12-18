package ru.chupaYchups.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.chupaYchups.dto.BookDto;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.chupaYchups.mongock.test.TestDatabaseChangeLog.*;

@SpringBootTest
@DisplayName("Тестирование того что сервис корректно")
class BookServiceImplIntegrationTest {

    private static final String AUTHOR_FIELD = "author";
    private static final String GENRE_FIELD = "genre";

    @Autowired
    private BookService bookService;

    @Test
    @DisplayName("ищет книги по параметрам")
    void testThatServiceCorrectlyFindBooksByParameters() {
        List<BookDto> bookList = bookService.findBooks(Optional.of(PUSHKIN_AUTHOR_NAME), Optional.of(NOVEL_GENRE_NAME), Optional.empty());
        assertThat(bookList).isEmpty();

        bookList = bookService.findBooks(Optional.of(PUSHKIN_AUTHOR_NAME), Optional.of(STORY_GENRE_NAME), Optional.empty());

        assertThat(bookList).
            isNotEmpty().
            hasSize(1).
            allSatisfy(bookDto -> {
                assertThat(bookDto).
                    hasFieldOrPropertyWithValue(AUTHOR_FIELD, PUSHKIN_AUTHOR_NAME).
                    hasFieldOrPropertyWithValue(GENRE_FIELD, STORY_GENRE_NAME);
            });
    }
}