package ru.chupaYchups.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Genre;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.chupaYchups.mongock.test.TestDatabaseChangeLog.*;


@DataMongoTest
@DisplayName("Тестирование того, что репозиторий книг корректно")
class BookRepositoryTest {

    private static final String TEST_GENRE_NAME = "test genre name";
    private static final String TEST_BOOK_NAME = "test book name";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("ищет книгу по идентификатору")
    void testThatCorrectlyFindBookById() {
        Book testBook = mongoTemplate.findById(WAR_AND_PEACE_BOOK_ID, Book.class);

        Optional<Book> foundBookOptional = bookRepository.findById(testBook.getId());

        assertThat(foundBookOptional).isPresent().get().isEqualToComparingFieldByField(testBook);
    }

    @Test
    @DisplayName("удаляет книгу")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testThatCorrectlyDeleteBook() {
        Book bookToDelete = mongoTemplate.findById(WAR_AND_PEACE_BOOK_ID, Book.class);

        bookRepository.delete(bookToDelete);

        assertThat(mongoTemplate.findById(WAR_AND_PEACE_BOOK_ID, Book.class)).isNull();
    }

    @Test
    @DisplayName("сохраняет новую книгу")
    void testThatCorrectlyPersistNewBook() {
        Author testAuthor = mongoTemplate.findById(PUSHKIN_AUTHOR_ID, Author.class);
        Genre testGenre = mongoTemplate.findById(STORY_GENRE_ID, Genre.class);
        Book newBook = new Book(TEST_BOOK_NAME, testAuthor, testGenre);

        Book returnedBook = bookRepository.save(newBook);

        assertThat(returnedBook.getId()).isNotNull();
        assertThat(returnedBook).isEqualToIgnoringNullFields(newBook);
        Book persistedBook = mongoTemplate.findById(returnedBook.getId(), Book.class);
        assertThat(persistedBook.getAuthor()).isEqualToComparingFieldByField(testAuthor);
        assertThat(persistedBook.getGenre()).isEqualToComparingFieldByField(testGenre);
    }

    @Test
    @DisplayName("обновляет книгу")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testThatCorrectlyUpdateBook() {
        Book testBook = mongoTemplate.findById(WAR_AND_PEACE_BOOK_ID, Book.class);
        Genre newGenre = new Genre(TEST_GENRE_NAME);
        testBook.setGenre(newGenre);

        Book returnedBook = bookRepository.save(testBook);

        assertThat(returnedBook).isEqualToComparingFieldByField(testBook);
        Book persistedBook = mongoTemplate.findById(returnedBook.getId(), Book.class);
        assertThat(persistedBook).isEqualToComparingFieldByField(testBook);
    }

    @Test
    @DisplayName("вовзращает все книги")
    void getAllBooks() {
        Book firstTestBook = mongoTemplate.findById(WAR_AND_PEACE_BOOK_ID, Book.class);
        Book secondTestBook = mongoTemplate.findById(RUSLAN_AND_LUDMILA_BOOK_ID, Book.class);

        List<Book> foundBooks = StreamSupport.stream(bookRepository.findAll().spliterator(), false).collect(Collectors.toList());

        assertThat(foundBooks).isNotEmpty().hasSize(2).containsExactly(firstTestBook, secondTestBook);
    }
}