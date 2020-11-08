package ru.chupaYchups.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Genre;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;


@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
class BookDaoJdbcTest {

    public static final String TEST_AUTHOR_NAME = "Тестовый автор";
    public static final String TEST_GENRE_NAME = "Тестовый жанр";
    public static final String TEST_BOOK_NAME = "Тестовая книга";

    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private GenreDao genreDao;

    @Test
    void insert() {
        Book expectedBook = new Book(null, new Date(), TEST_BOOK_NAME, authorDao.findByName(TEST_AUTHOR_NAME), genreDao.findByName(TEST_GENRE_NAME));
        long bookId = bookDao.insert(expectedBook);
        assertThat(bookId).isNotEqualTo(expectedBook.getId());
        Book actualBook = bookDao.findById(bookId);
        assertThat(actualBook).isEqualToIgnoringNullFields(expectedBook);
    }

    @Test
    void findAll() {
    }
}