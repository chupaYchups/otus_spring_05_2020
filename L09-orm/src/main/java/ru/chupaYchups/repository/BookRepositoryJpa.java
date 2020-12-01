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
                        "join b.author a " +
                        "join b.genre g " +
                        "where b.name = :name " +
                        "and a.id = :author_id " +
                        "and g.id = :genre_id ", Book.class);
        nameOptional.ifPresent(name ->
            typedQuery.setParameter("name", name));
        authorOptional.ifPresent(author ->
            typedQuery.setParameter("author_id", author.getId()));
        genreOptional.ifPresent(genre ->
            typedQuery.setParameter("genre_id", genre.getId()));
        return typedQuery.getResultList();
    }

    @Override
    public List<Book> getAllBooks() {
        TypedQuery<Book> typedQuery = entityManager.createQuery("select b from Book b " , Book.class);
        return typedQuery.getResultList();
    }
}
