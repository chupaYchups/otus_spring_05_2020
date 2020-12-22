package ru.chupaYchups.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Comment;
import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long>, CommentRepositoryCustom {
    List<Comment> findByBook(Book book);
}
