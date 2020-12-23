package ru.chupaYchups.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Genre;
import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    @Query("select b " +
            "from Book b " +
            "join fetch b.author a " +
            "join fetch b.genre g " +
            "where b.name = COALESCE(:name, b.name)" +
            "and a = COALESCE(:author, a)" +
            "and g = COALESCE(:genre, g)")
    List<Book> findAllByParams(@Param("name")String name, @Param("author")Author author, @Param("genre")Genre genre);
}
