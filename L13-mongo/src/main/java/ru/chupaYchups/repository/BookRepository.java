package ru.chupaYchups.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Genre;
import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    Book findByName(String name);
}
