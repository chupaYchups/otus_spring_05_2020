package ru.chupaYchups.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.chupaYchups.domain.Book;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {

    public static final long FIRST_TEST_BOOK_ID = 1l;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testThatCorrectlyFindBookById() {
        Book testBook = testEntityManager.find(Book.class, FIRST_TEST_BOOK_ID);
        Optional<Book> foundBookOptional = bookRepository.findById(FIRST_TEST_BOOK_ID);
        assertThat(foundBookOptional).isPresent().get().isEqualToComparingFieldByField(testBook);
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void findBooks() {
    }

    @Test
    void getAllBooks() {
    }
}