package ru.chupaYchups.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.chupaYchups.domain.Genre;
import ru.chupaYchups.repository.GenreRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Тестирование того, что репозиторий жанров корректно")
class GenreRepositoryJpaTest {

/*    private final static long TEST_GENRE_ID_FIRST = 1l;
    private final static String TEST_GENRE_NAME_FIRST = "Detective";

    private final static long TEST_GENRE_ID_SECOND = 2l;
    public static final String TEST_GENRE_NAME = "test genre name";
    public static final String NAME_FIELD = "name";
    public static final String TEST_GENRE_NEW_NAME = "test genre new name";

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("сохраняет новую сущность")
    void testThatRepositoryCorrectlySaveNewGenre() {
        Genre genreToSave = new Genre(TEST_GENRE_NAME);

        Genre returnedGenre = genreRepository.save(genreToSave);

        assertThat(returnedGenre).
                isNotNull().
                hasNoNullFieldsOrProperties().
                hasFieldOrPropertyWithValue(NAME_FIELD, genreToSave.getName());

        Genre persistedGenre = testEntityManager.find(Genre.class, returnedGenre.getId());

        assertThat(persistedGenre).isNotNull().isEqualToComparingFieldByField(returnedGenre);
    }

    @Test
    @DisplayName("обновляет сущность")
    void testThatRepositoryCorrectlyUpdateGenre() {
        Genre genreToUpdate = testEntityManager.find(Genre.class, TEST_GENRE_ID_FIRST);
        genreToUpdate.setName(TEST_GENRE_NEW_NAME);

        Genre returnedGenre = genreRepository.save(genreToUpdate);

        assertThat(returnedGenre).isEqualTo(genreToUpdate);

        Genre persistedGenre = testEntityManager.find(Genre.class, genreToUpdate.getId());

        assertThat(persistedGenre).isNotNull().isEqualToComparingFieldByField(genreToUpdate);
    }

    @Test
    @DisplayName("находит жанр по идентификатору")
    void testThatRepositoryCorrectlyFindGenreById() {
        Genre persistedGenre = testEntityManager.find(Genre.class, TEST_GENRE_ID_FIRST);

        Optional<Genre> foundGenreOptional = genreRepository.findById(TEST_GENRE_ID_FIRST);

        assertThat(foundGenreOptional).isNotEmpty().contains(persistedGenre);
    }

    @Test
    @DisplayName("находит жанр по имени")
    void testThatRepositoryCorrectlyFindGenreByName() {
        Genre persistedgenre = testEntityManager.find(Genre.class, TEST_GENRE_ID_FIRST);

        Optional<Genre> foundGenreOptional = genreRepository.findByName(TEST_GENRE_NAME_FIRST);

        assertThat(foundGenreOptional).isNotEmpty().containsSame(persistedgenre);
    }

    @Test
    @DisplayName("получает все жанры")
    void testThatRepositoryCorrectlyGetAllGenres() {
        Genre tolstoyGenre = testEntityManager.find(Genre.class, TEST_GENRE_ID_FIRST);
        Genre pushkinGenre = testEntityManager.find(Genre.class, TEST_GENRE_ID_SECOND);

        List<Genre> genreList = StreamSupport.stream(genreRepository.findAll().spliterator(), false).collect(Collectors.toList());

        assertThat(genreList).hasSize(2).contains(tolstoyGenre, pushkinGenre);
    }

    @Test
    @DisplayName("удаляет жанр")
    void testThatRepositoryCorrectlyDeleteGenre() {
        Genre genreToDelete = testEntityManager.find(Genre.class, TEST_GENRE_ID_FIRST);

        genreRepository.delete(genreToDelete);

        assertThat(testEntityManager.find(Genre.class, TEST_GENRE_ID_FIRST)).isNull();
    }*/
}