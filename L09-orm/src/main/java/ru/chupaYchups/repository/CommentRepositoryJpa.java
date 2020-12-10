package ru.chupaYchups.repository;

import org.springframework.stereotype.Repository;
import ru.chupaYchups.domain.Comment;
import ru.chupaYchups.repository.exception.NoSuchCommentException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class CommentRepositoryJpa implements CommentRepository {

    private static final String BOOK_PARAM = "book";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() != null) {
            return entityManager.merge(comment);
        } else {
            entityManager.persist(comment);
            return comment;
        }
    }

    @Override
    public Comment findById(Long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id)).
                orElseThrow(() -> new NoSuchCommentException(id));
    }

    @Override
    public void delete(Comment comment) {
        entityManager.remove(comment);
    }
}
