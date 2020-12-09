package ru.chupaYchups.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.chupaYchups.domain.Author;
import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
