package ru.chupaYchups.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ru.chupaYchups.domain.Book;

@Repository
@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public void removeAuthorBooks(String authorId) {
        Query query = Query.query(Criteria.where("author._id").is(authorId));
        mongoTemplate.remove(query, Book.class);
    }

    @Override
    public void removeGenreBooks(String authorId) {
        Query query = Query.query(Criteria.where("genre._id").is(authorId));
        mongoTemplate.remove(query, Book.class);
    }

    @Override
    public void clearCommentInfoFromBooks(String commentId) {
        Query query = Query.query(Criteria.where("$id").is(new ObjectId(commentId)));
        Update update = new Update().pull("comment", query);
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }
}
