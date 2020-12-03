package ru.chupaYchups.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.chupaYchups.domain.Genre;

@DataJpaTest
@Import(GenreRepositoryJpa.class)
//@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void save() {
        assert genreRepository.findById(1l) != null;
        assert testEntityManager.find(Genre.class, 1l) != null;

    }

    @Test
    void findById() {
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