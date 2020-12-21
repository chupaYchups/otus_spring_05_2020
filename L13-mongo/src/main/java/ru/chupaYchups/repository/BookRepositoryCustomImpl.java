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

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public void removeByAuthorId(String authorId) {
        Query query = Query.query(Criteria.where("author._id").is(authorId));
        List<Book> bookList = mongoTemplate.find(query, Book.class);
        bookList.forEach( book -> {
           mongoTemplate.remove(book);
        });
    }

    @Override
    public void removeByGenreId(String genreId) {
        Query query = Query.query(Criteria.where("genre._id").is(genreId));
        mongoTemplate.remove(query, Book.class);
    }

    @Override
    public void removeCommentRefWhereExist(String commentId) {
        Query query = Query.query(Criteria.where("$id").is(new ObjectId(commentId)));
        Update update = new Update().pull("comment", query);
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }
}
