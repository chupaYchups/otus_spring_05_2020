package ru.chupaYchups.repository;

import org.springframework.stereotype.Repository;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpa implements BookRepository {

    public static final String NAME_PARAM = "name";
    public static final String AUTHOR_ID_PARAM = "author_id";
    public static final String GENRE_ID_PARAM = "genre_id";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book save(Book book) {
        if (book.getId() != null) {
            return entityManager.merge(book);
        } else {
            entityManager.persist(book);
            entityManager.refresh(book);
            return book;
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public void delete(Book book) {
        entityManager.remove(book);
    }

    @Override
    public List<Book> findBooks(Optional<Author> authorOptional, Optional<Genre> genreOptional, Optional<String> nameOptional) {
        TypedQuery<Book> typedQuery = entityManager.createQuery(
                "select b " +
                        "from Book b " +
                        "join fetch b.author a " +
                        "join fetch b.genre g " +
                        "where b.name = COALESCE(:name, b.name)" +
                        "and a.id = COALESCE(:author_id, a.id)" +
                        "and g.id = COALESCE(:genre_id, g.id)", Book.class);
        typedQuery.setParameter(NAME_PARAM, nameOptional.orElse(null));
        typedQuery.setParameter(AUTHOR_ID_PARAM, authorOptional.map(author -> author.getName()).orElse(null));
        typedQuery.setParameter(GENRE_ID_PARAM, genreOptional.map(genre -> genre.getName()).orElse(null));

        return typedQuery.getResultList();
    }

    @Override
    public List<Book> getAllBooks() {
        TypedQuery<Book> typedQuery = entityManager.createQuery("select b from Book b join fetch b.author a join fetch b.genre" , Book.class);
        return typedQuery.getResultList();
    }
}
