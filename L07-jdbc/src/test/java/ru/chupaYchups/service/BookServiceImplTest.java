package ru.chupaYchups.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
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
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

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
    private Genre testGenre;

    @Configuration
    @ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ShellComponent.class))
    static class TestConfiguration {}

    @BeforeEach
    void setUp() {
        testAuthor = new Author("Test author");
        testGenre = new Genre("Test genre");
    }

    @Test
    void testThatServiceCorrectlyQueryAllBooks() {
        List<Book> expectedDtoList = List.of(new Book(1l, "test book 1", testAuthor, testGenre),
                new Book(2l, "test book 2", testAuthor, testGenre));
        BDDMockito.given(bookDao.findBooks(Optional.empty(), Optional.empty(), Optional.empty())).willReturn(expectedDtoList);

        List<BookDto> actualDtoList = bookService.findBooks(Optional.empty(), Optional.empty(), Optional.empty());

        assertThat(actualDtoList).hasSameSizeAs(expectedDtoList);
        //assertThat(actualDtoList.get(0)).isEqualToComparingFieldByField(expectedDtoList.get(0));
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