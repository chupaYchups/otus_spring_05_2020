package ru.chupaYchups.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chupaYchups.domain.Genre;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    Optional<Genre> findByName(String name);
}
