package ru.chupaYchups.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.chupaYchups.domain.Author;
import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByName(String name);
}
