package ru.chupaYchups.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.chupaYchups.dao.AuthorDao;
import ru.chupaYchups.dao.BookDao;
import ru.chupaYchups.dao.GenreDao;

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

    @Test
    void testThatServiceCorrectlyQueryAllBooks() {

        bookService.findBooks(Optional.empty(), Optional.empty(), Optional.empty());

    }

    @Test
    void testThatServiceCorrectlyQueryBooksByParameters() {
        BDDMockito.given()
        bookService.findBooks(Optional.empty(), Optional.empty(), Optional.empty());
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