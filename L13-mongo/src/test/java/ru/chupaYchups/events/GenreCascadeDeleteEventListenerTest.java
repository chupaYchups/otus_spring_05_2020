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
import ru.chupaYchups.domain.Genre;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.chupaYchups.mongock.test.TestDatabaseChangeLog.NOVEL_GENRE_ID;

@DataMongoTest
@ComponentScan("ru.chupaYchups.events")
@DisplayName("Тестирование того что листенер удаления жанра")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class GenreCascadeDeleteEventListenerTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    @DisplayName("корректно каскадно удаляет книги такого жанра")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testThatAuthorBooksCascadeDeletesWithAuthor() {
        Genre genreToDelete = mongoTemplate.findById(NOVEL_GENRE_ID, Genre.class);
        Query query = Query.query(Criteria.where("genre._id").is(NOVEL_GENRE_ID));
        assertThat(mongoTemplate.find(query, Book.class)).isNotEmpty();

        mongoTemplate.remove(genreToDelete);

        assertThat(mongoTemplate.find(query, Book.class)).isEmpty();
    }

}