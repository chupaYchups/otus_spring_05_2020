package ru.chupaYchups.repository;

import org.springframework.stereotype.Repository;
import ru.chupaYchups.domain.Genre;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryJpa implements GenreRepository {

    public static final String NAME_PARAM = "name";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() != null) {
            return entityManager.merge(genre);
        } else {
            entityManager.persist(genre);
            entityManager.refresh(genre);
            return genre;
        }
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    @Override
    public Optional<Genre> findByName(String name) {
        TypedQuery<Genre> typedQuery = entityManager.createQuery(
                "select g from Genre g where g.name=:name", Genre.class);
        typedQuery.setParameter(NAME_PARAM, name);
        try {
            return Optional.of(typedQuery.getSingleResult());
        } catch (NoResultException exc) {
            return Optional.empty();
        }
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> typedQuery = entityManager.createQuery(
                "select g from Genre g", Genre.class);
        return typedQuery.getResultList();
    }

    @Override
    public void delete(Genre genre) {
        entityManager.remove(genre);
    }
}
