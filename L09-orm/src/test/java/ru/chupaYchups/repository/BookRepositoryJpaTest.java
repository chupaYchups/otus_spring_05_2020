package ru.chupaYchups.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Genre;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(BookRepositoryJpa.class)
@DisplayName("Тестирование того, что репозиторий книг корректно")
class BookRepositoryJpaTest {

    private static final long FIRST_TEST_BOOK_ID = 1l;
    private static final long SECOND_TEST_BOOK_ID = 2l;
    private static final long FIRST_TEST_GENRE_ID = 1l;
    private static final long FIRST_TEST_AUTHOR_ID = 1l;
    private static final String TEST_AUTHOR_NAME = "test author name";
    private static final String TEST_GENRE_NAME = "test genre name";
    private static final String TEST_BOOK_NAME = "test book name";

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("ищет книгу по идентификатору")
    void testThatCorrectlyFindBookById() {
        Book testBook = testEntityManager.find(Book.class, FIRST_TEST_BOOK_ID);
        Book foundBook = bookRepository.findById(FIRST_TEST_BOOK_ID);
        assertThat(foundBook).isEqualToComparingFieldByField(testBook);
    }

    @Test
    @DisplayName("удаляет книгу")
    void testThatCorrectlyDeleteBook() {
        Book testBook = testEntityManager.find(Book.class, FIRST_TEST_BOOK_ID);
        bookRepository.delete(testBook);
        assertThat(testEntityManager.find(Book.class, FIRST_TEST_BOOK_ID)).isNull();
    }

    @Test
    @DisplayName("сохраняет новую книгу")
    void testThatCorrectlyPersistNewBook() {
        Author newAuthor = new Author(TEST_AUTHOR_NAME);
        Genre newGenre = new Genre(TEST_GENRE_NAME);
        Book newBook = new Book(TEST_BOOK_NAME, newAuthor, newGenre);

        Book returnedBook = bookRepository.save(newBook);

        assertThat(returnedBook.getId()).isNotNull();
        assertThat(returnedBook).isEqualToIgnoringNullFields(newBook);

        Book savedBook = testEntityManager.find(Book.class, returnedBook.getId());
        assertThat(savedBook.getAuthor()).isEqualToIgnoringNullFields(newAuthor);
        assertThat(savedBook.getGenre()).isEqualToIgnoringNullFields(newGenre);
    }

    @Test
    @DisplayName("обновляет книгу")
    void testThatCorrectlyUpdateBook() {
        Book testBook = testEntityManager.find(Book.class, FIRST_TEST_BOOK_ID);
        Genre newGenre = new Genre(TEST_GENRE_NAME);
        testBook.setGenre(newGenre);

        Book returnedBook = bookRepository.save(testBook);

        assertThat(returnedBook).isEqualToIgnoringNullFields(testBook);

        Book savedBook = testEntityManager.find(Book.class, returnedBook.getId());
        assertThat(savedBook).isEqualToComparingFieldByField(testBook);
    }

    @Test
    @DisplayName("ищет книги по параметрам")
    void testThatCorrectlyFindBookByParameters() {
        Book testBook = testEntityManager.find(Book.class, FIRST_TEST_BOOK_ID);
        Genre testBookGenre = testEntityManager.find(Genre.class, FIRST_TEST_GENRE_ID);
        Author testBookAuthor = testEntityManager.find(Author.class, FIRST_TEST_AUTHOR_ID);

        List<Book> foundBooks = bookRepository.findBooks(Optional.of(testBookAuthor), Optional.of(testBookGenre), Optional.empty());

        assertThat(foundBooks).isNotEmpty().hasSize(1).contains(testBook);
    }

    @Test
    @DisplayName("вовзращает все книги")
    void getAllBooks() {
        Book firstTestBook = testEntityManager.find(Book.class, FIRST_TEST_BOOK_ID);
        Book secondTestBook = testEntityManager.find(Book.class, SECOND_TEST_BOOK_ID);

        List<Book> foundBooks = bookRepository.getAllBooks();

        assertThat(foundBooks).isNotEmpty().hasSize(2).contains(firstTestBook, secondTestBook);
    }
}