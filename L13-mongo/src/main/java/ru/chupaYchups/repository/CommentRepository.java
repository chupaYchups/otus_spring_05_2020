package ru.chupaYchups.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Comment;
import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByBook(Book book);
}
