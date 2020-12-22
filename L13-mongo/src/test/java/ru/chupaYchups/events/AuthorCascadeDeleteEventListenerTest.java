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
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.chupaYchups.mongock.test.TestDatabaseChangeLog.TOLSTOY_AUTHOR_ID;

@DataMongoTest
@ComponentScan("ru.chupaYchups.events")
@DisplayName("Тестирование того что листенер удаления автора")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class AuthorCascadeDeleteEventListenerTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    @DisplayName("корректно каскадно удаляет его книги")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testThatAuthorBooksCascadeDeletesWithAuthor() {
        Author authorToDelete = mongoTemplate.findById(TOLSTOY_AUTHOR_ID, Author.class);
        Query query = Query.query(Criteria.where("author._id").is(TOLSTOY_AUTHOR_ID));
        assertThat(mongoTemplate.find(query, Book.class)).isNotEmpty();

        mongoTemplate.remove(authorToDelete);

        assertThat(mongoTemplate.find(query, Book.class)).isEmpty();
    }
}