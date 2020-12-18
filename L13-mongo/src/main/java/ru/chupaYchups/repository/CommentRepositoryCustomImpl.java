package ru.chupaYchups.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.chupaYchups.domain.Book;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public void removeBookComments(String bookId) {
        Query query = Query.query(Criteria.where("book._id").is(bookId));
        mongoTemplate.remove(query, Book.class);
    }
}
