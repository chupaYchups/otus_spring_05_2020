package ru.chupaYchups.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import ru.chupaYchups.domain.Author;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.chupaYchups.mongock.test.TestDatabaseChangeLog.*;

@DataMongoTest
@DisplayName("Тестирование того, что репозиторий авторов корректно")
class AuthorRepositoryMongoTest {

    public static final String NAME_FIELD = "name";
    public static final String TEST_AUTHOR_NEW_NAME = "test author new name";
    public static final String TEST_AUTHOR_NAME = "test author name";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("сохраняет новую сущность")
    void testThatRepositoryCorrectlySaveNewAuthor() {
        Author authorToSave = new Author(TEST_AUTHOR_NAME);

        Author returnedAuthor = authorRepository.save(authorToSave);

        assertThat(returnedAuthor).
            isNotNull().
            hasNoNullFieldsOrProperties().
            hasFieldOrPropertyWithValue(NAME_FIELD, authorToSave.getName());

        Author persistedAuthor = mongoTemplate.findById(returnedAuthor.getId(), Author.class);

        assertThat(persistedAuthor).isNotNull().isEqualToComparingFieldByField(returnedAuthor);
    }

   @Test
    @DisplayName("обновляет сущность")
    void testThatRepositoryCorrectlyUpdateAuthor() {
        Author authorToUpdate = mongoTemplate.findById(TOLSTOY_AUTHOR_ID, Author.class);
        authorToUpdate.setName(TEST_AUTHOR_NEW_NAME);

        Author returnedAuthor = authorRepository.save(authorToUpdate);

        assertThat(returnedAuthor).isEqualTo(authorToUpdate);

        Author persistedAuthor = mongoTemplate.findById(TOLSTOY_AUTHOR_ID, Author.class);

        assertThat(persistedAuthor).isNotNull().isEqualToComparingFieldByField(authorToUpdate);
    }


    @Test
    @DisplayName("находит автора по идентификатору")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void testThatRepositoryCorrectlyFindAuthorById() {
        Author persistedAuthor = mongoTemplate.findById(TOLSTOY_AUTHOR_ID, Author.class);

        Optional<Author> foundAuthorOptional = authorRepository.findById(TOLSTOY_AUTHOR_ID);

        assertThat(foundAuthorOptional).isNotEmpty().contains(persistedAuthor);
    }


    @Test
    @DisplayName("находит автора по имени")
    void testThatRepositoryCorrectlyFindAuthorByName() {
        Author persistedAuthor = mongoTemplate.findById(TOLSTOY_AUTHOR_ID, Author.class);

        Optional<Author> foundAuthorOptional = authorRepository.findByName(TOLSTOY_AUTHOR_NAME);

        assertThat(foundAuthorOptional).isNotEmpty().contains(persistedAuthor);
    }


    @Test
    @DisplayName("получает всех авторов")
    void testThatRepositoryCorrectlyGetAllAuthors() {
        Author tolstoyAuthor = mongoTemplate.findById(TOLSTOY_AUTHOR_ID, Author.class);
        Author pushkinAuthor = mongoTemplate.findById(PUSHKIN_AUTHOR_ID, Author.class);

        List<Author> authorList = StreamSupport.stream(authorRepository.findAll().spliterator(), false).collect(Collectors.toList());

        assertThat(authorList).hasSize(2).contains(tolstoyAuthor, pushkinAuthor);
    }

    @Test
    @DisplayName("удаляет автора")
    void testThatRepositoryCorrectlyDeleteAuthor() {
        Author authorToDelete = mongoTemplate.findById(TOLSTOY_AUTHOR_ID, Author.class);

        authorRepository.delete(authorToDelete);

        assertThat(mongoTemplate.findById(TOLSTOY_AUTHOR_ID, Author.class)).isNull();
    }
}
