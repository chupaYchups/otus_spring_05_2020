package ru.chupaYchups.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.shell.standard.ShellComponent;
import ru.chupaYchups.dao.AuthorDao;
import ru.chupaYchups.dao.BookDao;
import ru.chupaYchups.dao.GenreDao;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Genre;
import ru.chupaYchups.dto.BookDto;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
@DisplayName("Тестирование того что сервис корректно")
class BookServiceImplTest {

    @MockBean
    private BookDao bookDao;
    @MockBean
    private AuthorDao authorDao;
    @MockBean
    private GenreDao genreDao;

    @Autowired
    private BookService bookService;

    private Author testAuthor;
    private Author testAuthor2;
    private Genre testGenre;
    private Genre testGenre2;

    @Configuration
    @ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ShellComponent.class))
    static class TestConfiguration {}

    @BeforeEach
    void setUp() {
        testAuthor = new Author("Test author");
        testGenre = new Genre("Test genre");
        testAuthor2 = new Author("Test author 2");
        testGenre2 = new Genre("Test genre 2");
    }

    @Test
    void testThatServiceCorrectlyQueryAllBooks() {

        List<Book> testBookList = List.of(new Book(1L, "test book 1", testAuthor, testGenre),
                new Book(2l, "test book 2", testAuthor2, testGenre2));

        Map<Long, Book> testBookMap = testBookList.stream().collect(Collectors.toMap(Book::getId, book -> book));
        BDDMockito.given(bookDao.findBooks(Optional.empty(), Optional.empty(), Optional.empty())).willReturn(testBookList);

        List<BookDto> actualDtoList = bookService.findBooks(Optional.empty(), Optional.empty(), Optional.empty());

        assertThat(actualDtoList).hasSameSizeAs(testBookList).allSatisfy(bookDto -> {
            assertThat(bookDto).isEqualToComparingOnlyGivenFields(testBookMap.get(bookDto.getId()), "id", "name");
            assertThat(bookDto.getAuthor()).isEqualTo(testBookMap.get(bookDto.getId()).getAuthor().getName());
            assertThat(bookDto.getGenre()).isEqualTo(testBookMap.get(bookDto.getId()).getGenre().getName());
        });

        BDDMockito.then(bookDao).should(Mockito.atMostOnce()).findBooks(Optional.empty(), Optional.empty(), Optional.empty());
    }

    @Test
    void testThatServiceCorrectlyQueryBooksByParameters() {
    }

    @Test
    void addBook() {
    }

    @Test
    void updateBookById() {
    }

    @Test
    void deleteBookById() {
    }
}