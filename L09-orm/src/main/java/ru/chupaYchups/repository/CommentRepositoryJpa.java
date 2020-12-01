package ru.chupaYchups.repository;

import org.springframework.stereotype.Repository;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Comment;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() != null) {
            return entityManager.merge(comment);
        } else {
            entityManager.persist(comment);
            entityManager.refresh(comment);
            return comment;
        }
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    @Override
    public void delete(Comment comment) {
        entityManager.remove(comment);
    }

    @Override
    public List<Comment> findByBook(Book book) {
        return null;
    }
}
