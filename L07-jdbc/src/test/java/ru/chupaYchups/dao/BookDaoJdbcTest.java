package ru.chupaYchups.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
@DisplayName("Тестирование того что ДАО корректно")
class BookDaoJdbcTest {

    public static final String TEST_AUTHOR_NAME = "Test author";
    public static final String TEST_AUTHOR_2_NAME = "Test author 2";
    public static final String TEST_GENRE_NAME = "Test genre";
    public static final String TEST_GENRE_2_NAME = "Test genre";
    public static final String TEST_BOOK_NAME = "Test book";
    public static final long TEST_BOOK_ID = 4;
    public static final long TEST_BOOK_2_ID = 5;

    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private GenreDao genreDao;

    @Test
    @DisplayName("сохраняет данные в БД")
    void testThatDaoCorrectlyInsertDataInDB() {
        Author testAuthor = authorDao.findByName(TEST_AUTHOR_NAME).orElseThrow();
        Genre testGenre = genreDao.findByName(TEST_GENRE_NAME).orElseThrow();
        Book expectedBook = new Book(TEST_BOOK_NAME, testAuthor, testGenre);

        long bookId = bookDao.insert(expectedBook);

        assertThat(bookId).isNotEqualTo(expectedBook.getId());
        Optional<Book> actualBookOptional = bookDao.findById(bookId);
        assertThat(actualBookOptional).isNotEmpty().get().isEqualToIgnoringNullFields(expectedBook);
    }

    @Test
    @DisplayName("находит книгу по имени")
    void testThatDaoCorrectlyFindBookByName() {
        Book expectedTestBook = bookDao.findById(TEST_BOOK_ID).get();

        List<Book> bookList = bookDao.findBooks(Optional.empty(), Optional.empty(), Optional.of(TEST_BOOK_NAME));

        assertThat(bookList).isNotEmpty().hasSize(1).contains(expectedTestBook);
    }

    @Test
    @DisplayName("находит книги по автору и жанру")
    void testThatDaoCorrectlyFindBooksByAuthorAndGenre() {
        Author testAuthor = authorDao.findByName(TEST_AUTHOR_NAME).orElseThrow();
        Genre testGenre = genreDao.findByName(TEST_GENRE_NAME).orElseThrow();
        Book expectedTestBook1 = bookDao.findById(TEST_BOOK_ID).orElseThrow();
        Book expectedTestBook2 = bookDao.findById(TEST_BOOK_2_ID).orElseThrow();

        List<Book> bookList = bookDao.findBooks(Optional.of(testAuthor), Optional.of(testGenre), Optional.empty());

        assertThat(bookList).isNotEmpty().hasSize(2).containsExactly(expectedTestBook1, expectedTestBook2);
    }

    @Test
    @DisplayName("обновляет данные в БД")
    void testThatDaoCorrectlyUpdateDataInDb() {
        Author testAuthor = authorDao.findByName(TEST_AUTHOR_NAME).orElseThrow();
        Genre testGenre = genreDao.findByName(TEST_GENRE_NAME).orElseThrow();
        Author testAuthor2 = authorDao.findByName(TEST_AUTHOR_2_NAME).orElseThrow();
        Genre testGenre2 = genreDao.findByName(TEST_GENRE_2_NAME).orElseThrow();
        Book testBookToUpdate = bookDao.findById(TEST_BOOK_ID).orElseThrow();
        assertThat(testBookToUpdate.getAuthor()).isEqualTo(testAuthor);
        assertThat(testBookToUpdate.getGenre()).isEqualTo(testGenre);

        testBookToUpdate.setAuthor(testAuthor2);
        testBookToUpdate.setGenre(testGenre2);
        bookDao.update(testBookToUpdate);

        Book updatedTestBook = bookDao.findById(TEST_BOOK_ID).get();
        assertThat(updatedTestBook).isEqualTo(testBookToUpdate);
    }

    @Test
    @DisplayName("удаляет данные из БД")
    void testThatDaoCorrectlyDeleteDataFromDb() {
        Book testBookToDelete = bookDao.findById(TEST_BOOK_ID).get();
        bookDao.delete(testBookToDelete);
        assertThat(bookDao.findById(TEST_BOOK_ID)).isEmpty();
    }

}