package ru.chupaYchups.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import ru.chupaYchups.domain.Genre;
import static ru.chupaYchups.mongock.test.TestDatabaseChangeLog.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Тестирование того, что репозиторий жанров корректно")
class GenreRepositoryTest {

    private static final String TEST_GENRE_NAME = "test genre name";
    private static final String TEST_GENRE_NEW_NAME = "test genre new name";
    private static final String NAME_FIELD = "name";

    @Autowired
    private MongoTemplate mongoTemplate;

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

        Genre persistedGenre = mongoTemplate.findById(returnedGenre.getId(), Genre.class);

        assertThat(persistedGenre).isNotNull().isEqualToComparingFieldByField(returnedGenre);
    }

    @Test
    @DisplayName("обновляет сущность")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testThatRepositoryCorrectlyUpdateGenre() {
        Genre genreToUpdate = mongoTemplate.findById(NOVEL_GENRE_ID, Genre.class);
        genreToUpdate.setName(TEST_GENRE_NEW_NAME);

        Genre returnedGenre = genreRepository.save(genreToUpdate);

        assertThat(returnedGenre).isEqualTo(genreToUpdate);

        Genre persistedGenre = mongoTemplate.findById(genreToUpdate.getId(), Genre.class);

        assertThat(persistedGenre).isNotNull().isEqualToComparingFieldByField(genreToUpdate);
    }

    @Test
    @DisplayName("находит жанр по идентификатору")
    void testThatRepositoryCorrectlyFindGenreById() {
        Genre persistedGenre = mongoTemplate.findById(NOVEL_GENRE_ID, Genre.class);

        Optional<Genre> foundGenreOptional = genreRepository.findById(NOVEL_GENRE_ID);

        assertThat(foundGenreOptional).isNotEmpty().contains(persistedGenre);
    }

    @Test
    @DisplayName("находит жанр по имени")
    void testThatRepositoryCorrectlyFindGenreByName() {
        Genre persistedGenre = mongoTemplate.findById(NOVEL_GENRE_ID, Genre.class);

        Optional<Genre> foundGenreOptional = genreRepository.findByName(NOVEL_GENRE_NAME);

        assertThat(foundGenreOptional).isNotEmpty().contains(persistedGenre);
    }

    @Test
    @DisplayName("получает все жанры")
    void testThatRepositoryCorrectlyGetAllGenres() {
        Genre novelGenre = mongoTemplate.findById(NOVEL_GENRE_ID, Genre.class);
        Genre storyGenre = mongoTemplate.findById(STORY_GENRE_ID, Genre.class);

        List<Genre> genreList = StreamSupport.stream(genreRepository.findAll().spliterator(), false).collect(Collectors.toList());

        assertThat(genreList).hasSize(2).contains(novelGenre, storyGenre);
    }

    @Test
    @DisplayName("удаляет жанр")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testThatRepositoryCorrectlyDeleteGenre() {
        Genre genreToDelete = mongoTemplate.findById(NOVEL_GENRE_ID, Genre.class);

        genreRepository.delete(genreToDelete);

        assertThat(mongoTemplate.findById(NOVEL_GENRE_ID, Genre.class)).isNull();
    }
}