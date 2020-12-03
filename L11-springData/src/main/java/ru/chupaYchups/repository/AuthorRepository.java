package ru.chupaYchups.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chupaYchups.domain.Author;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
