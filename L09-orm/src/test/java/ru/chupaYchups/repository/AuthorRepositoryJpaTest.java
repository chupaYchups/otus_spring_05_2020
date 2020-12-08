package ru.chupaYchups.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.chupaYchups.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(AuthorRepositoryJpa.class)
@DisplayName("Тестирование того, что репозиторий авторов корректно")
class AuthorRepositoryJpaTest {

    private final static long TEST_AUTHOR_ID_FIRST = 1l;
    private final static String TEST_AUTHOR_NAME_FIRST = "Tolstoy";

    private final static long TEST_AUTHOR_ID_SECOND = 2l;

    public static final String TEST_AUTHOR_NAME = "test author name";
    public static final String NAME_FIELD = "name";
    public static final String TEST_AUTHOR_NEW_NAME = "test author new name";

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AuthorRepositoryJpa authorRepositoryJpa;

    @Test
    @DisplayName("сохраняет новую сущность")
    void testThatRepositoryCorrectlySaveNewAuthor() {
        Author authorToSave = new Author(TEST_AUTHOR_NAME);

        Author returnedAuthor = authorRepositoryJpa.save(authorToSave);

        assertThat(returnedAuthor).
            isNotNull().
            hasNoNullFieldsOrProperties().
            hasFieldOrPropertyWithValue(NAME_FIELD, authorToSave.getName());

        Author persistedAuthor = testEntityManager.find(Author.class, returnedAuthor.getId());

        assertThat(persistedAuthor).isNotNull().isEqualToComparingFieldByField(returnedAuthor);
    }

    @Test
    @DisplayName("обновляет сущность")
    void testThatRepositoryCorrectlyUpdateAuthor() {
        Author authorToUpdate = testEntityManager.find(Author.class, TEST_AUTHOR_ID_FIRST);
        authorToUpdate.setName(TEST_AUTHOR_NEW_NAME);

        Author returnedAuthor = authorRepositoryJpa.save(authorToUpdate);

        assertThat(returnedAuthor).isEqualTo(authorToUpdate);

        Author persistedAuthor = testEntityManager.find(Author.class, authorToUpdate.getId());

        assertThat(persistedAuthor).isNotNull().isEqualToComparingFieldByField(authorToUpdate);
    }

    @Test
    @DisplayName("находит автора по идентификатору")
    void testThatRepositoryCorrectlyFindAuthorById() {
        Author persistedAuthor = testEntityManager.find(Author.class, TEST_AUTHOR_ID_FIRST);

        Optional<Author> foundAuthorOptional = authorRepositoryJpa.findById(TEST_AUTHOR_ID_FIRST);

        assertThat(foundAuthorOptional).isNotEmpty().containsSame(persistedAuthor);
    }

    @Test
    @DisplayName("находит автора по имени")
    void testThatRepositoryCorrectlyFindAuthorByName() {
        Author persistedAuthor = testEntityManager.find(Author.class, TEST_AUTHOR_ID_FIRST);

        Optional<Author> foundAuthorOptional = authorRepositoryJpa.findByName(TEST_AUTHOR_NAME_FIRST);

        assertThat(foundAuthorOptional).isNotEmpty().containsSame(persistedAuthor);
    }

    @Test
    @DisplayName("получает всех авторов")
    void testThatRepositoryCorrectlyGetAllAuthors() {
        Author tolstoyAuthor = testEntityManager.find(Author.class, TEST_AUTHOR_ID_FIRST);
        Author pushkinAuthor = testEntityManager.find(Author.class, TEST_AUTHOR_ID_SECOND);

        List<Author> authorList = authorRepositoryJpa.getAll();

        assertThat(authorList).hasSize(2).contains(tolstoyAuthor, pushkinAuthor);
    }

    @Test
    @DisplayName("удаляет автора")
    void testThatRepositoryCorrectlyDeleteAuthor() {
        Author authorToDelete = testEntityManager.find(Author.class, TEST_AUTHOR_ID_FIRST);

        authorRepositoryJpa.delete(authorToDelete);

        assertThat(testEntityManager.find(Author.class, TEST_AUTHOR_ID_FIRST)).isNull();
    }
}