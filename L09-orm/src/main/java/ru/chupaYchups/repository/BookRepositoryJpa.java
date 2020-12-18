package ru.chupaYchups.repository;

import org.springframework.stereotype.Repository;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Genre;
import ru.chupaYchups.repository.exception.NoSuchBookException;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpa implements BookRepository {

    private static final String NAME_PARAM = "name";
    private static final String AUTHOR_PARAM = "author";
    private static final String GENRE_PARAM = "genre";
    public static final String JAVAX_PERSISTENCE_FETCHGRAPH_HINT = "javax.persistence.fetchgraph";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book save(Book book) {
        if (book.getId() != null) {
            return entityManager.merge(book);
        } else {
            entityManager.persist(book);
            return book;
        }
    }

    @Override
    public Book findById(Long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id)).
                orElseThrow(() -> new NoSuchBookException(id));
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
                        "where b.name = COALESCE(:name, b.name)" +
                        "and b.author = COALESCE(:author, b.author)" +
                        "and b.genre = COALESCE(:genre, b.genre)", Book.class);
        typedQuery.setHint(JAVAX_PERSISTENCE_FETCHGRAPH_HINT, entityManager.getEntityGraph("book's-author-genre-entity-graph"));
        typedQuery.setParameter(NAME_PARAM, nameOptional.orElse(null));
        typedQuery.setParameter(AUTHOR_PARAM, authorOptional.orElse(null));
        typedQuery.setParameter(GENRE_PARAM, genreOptional.orElse(null));

        return typedQuery.getResultList();
    }

    @Override
    public List<Book> getAllBooks() {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("book's-author-genre-entity-graph");
        TypedQuery<Book> typedQuery = entityManager.createQuery("select b from Book b" , Book.class);
        typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);
        return typedQuery.getResultList();
    }
}
