package ru.chupaYchups.repository;

import org.springframework.stereotype.Repository;
import ru.chupaYchups.domain.Author;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author save(Author author) {
        if (author.getId() != null) {
            return entityManager.merge(author);
        } else {
            entityManager.persist(author);
            return author;
        }
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public Optional<Author> findByName(String name) {
        TypedQuery<Author> typedQuery = entityManager.createQuery(
                "select a from Author a where a.name = :name", Author.class);
        typedQuery.setParameter("name", name);
        try {
            return Optional.of(typedQuery.getSingleResult());
        } catch (NoResultException exc) {
            return Optional.empty();
        }
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> typedQuery = entityManager.createQuery(
                "select a from Author a", Author.class);
        return typedQuery.getResultList();
    }

    @Override
    public void delete(Author author) {
        entityManager.remove(author);
    }
}
