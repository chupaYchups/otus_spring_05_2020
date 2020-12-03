package ru.chupaYchups.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chupaYchups.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
