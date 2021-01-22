package ru.chupaYchups.events;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.annotation.DirtiesContext;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Comment;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.chupaYchups.mongock.test.TestDatabaseChangeLog.WAR_AND_PEACE_BOOK_ID;

@DataMongoTest
@ComponentScan("ru.chupaYchups.events")
@DisplayName("Тестирование того что листенер удаления книги")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class BookCascadeDeleteEventListenerTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    @DisplayName("корректно каскадно удаляет её комментарии")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testThatAuthorBooksCascadeDeletesWithAuthor() {
        Book bookToDelete = mongoTemplate.findById(WAR_AND_PEACE_BOOK_ID, Book.class);
        Query query = Query.query(Criteria.where("book.id").is(WAR_AND_PEACE_BOOK_ID));
        assertThat(mongoTemplate.find(query, Comment.class)).isNotEmpty();

        mongoTemplate.remove(bookToDelete);

        assertThat(mongoTemplate.find(query, Comment.class)).isEmpty();
    }

}