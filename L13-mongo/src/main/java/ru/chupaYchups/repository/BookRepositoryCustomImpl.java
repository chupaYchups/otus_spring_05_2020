package ru.chupaYchups.repository;

import lombok.RequiredArgsConstructor;
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

    private MongoTemplate mongoTemplate;

    @Override
    public void removeAuthorBooks(String authorId) {
        Query query = Query.query(Criteria.where("$id").is(new ObjectId(authorId)));
        Update update = new Update().pull("author", query);
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }
}
