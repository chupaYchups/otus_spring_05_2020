package ru.chupaYchups.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
public class AuthorRepositoryWithListenerTest {

    @Autowired
    private AuthorRepository authorRepository;



}
