package ru.chupaYchups.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class BookRepositoryJpaTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void save() {
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