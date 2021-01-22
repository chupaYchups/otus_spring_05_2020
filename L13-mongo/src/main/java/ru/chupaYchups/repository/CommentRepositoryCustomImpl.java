package ru.chupaYchups.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.chupaYchups.domain.Comment;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public void removeByBookId(String bookId) {
        Query query = Query.query(Criteria.where("book.id").is(bookId));
        List<Comment> comments = mongoTemplate.find(query, Comment.class);
        comments.forEach(comment -> {
            mongoTemplate.remove(comment);
        });
    }
}
