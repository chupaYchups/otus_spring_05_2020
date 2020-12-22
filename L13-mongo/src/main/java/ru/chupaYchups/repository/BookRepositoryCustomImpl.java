package ru.chupaYchups.repository;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ru.chupaYchups.domain.Book;
import java.util.List;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Data
    private class ArraySizeProjection {
        private int size;
    }

    @Override
    public void removeByAuthorId(String authorId) {
        Query query = Query.query(Criteria.where("author._id").is(authorId));
        removeByQuery(query);
    }

    @Override
    public void removeByGenreId(String genreId) {
        Query query = Query.query(Criteria.where("genre._id").is(genreId));
        removeByQuery(query);
    }

    private void removeByQuery(Query query) {
        List<Book> bookList = mongoTemplate.find(query, Book.class);
        bookList.forEach(book -> {
            mongoTemplate.remove(book);
        });
    }

    @Override
    public void removeCommentRefWhereExist(String commentId) {
        val query = Query.query(Criteria.where("$id").is(commentId));
        val update = new Update().pull("comments", query);
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }

    @Override
    public long getCommentsArrayLengthById(String bookId) {
        val aggregation = Aggregation.newAggregation(
            match(where("id").is(bookId)),
            project().
                andExclude("_id").
                and("comments").
                size().
                as("size"));

        val arraySizeProjection =
                mongoTemplate.aggregate(aggregation, Book.class, ArraySizeProjection.class).getUniqueMappedResult();

        return arraySizeProjection == null ? 0 : arraySizeProjection.getSize();
    }
}
