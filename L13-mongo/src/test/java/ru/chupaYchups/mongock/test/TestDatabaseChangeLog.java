package ru.chupaYchups.mongock.test;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Comment;
import ru.chupaYchups.domain.Genre;

@ChangeLog
public class TestDatabaseChangeLog {

    public final static String TOLSTOY_AUTHOR_ID = "author-test-id-tolstoy";
    public final static String TOLSTOY_AUTHOR_NAME = "Tolstoy";

    public final static String PUSHKIN_AUTHOR_ID = "author-test-id-pushkin";
    public final static String PUSHKIN_AUTHOR_NAME = "Pushkin";

    public static final String NOVEL_GENRE_NAME = "Novel";
    public static final String NOVEL_GENRE_ID = "genre-test-id-novel";

    public static final String STORY_GENRE_NAME = "Story";
    public static final String STORY_GENRE_ID = "genre-test-id-story";

    public static final String WAR_AND_PEACE_BOOK_NAME = "War and peace";
    public static final String WAR_AND_PEACE_BOOK_ID = "book-test-id-war-and-peace";

    public static final String RUSLAN_AND_LUDMILA_BOOK_NAME = "Ruslan and Ludmila";
    public static final String RUSLAN_AND_LUDMILA_BOOK_ID = "book-test-id-ruslan-and-ludmila";

    public static final String CHUPA_Y_CHUPS = "chupaYchups";
    public static final String COMMENT_TEST_ID_RL_GOOD = "comment-test-id-r&l-good";
    public static final String COMMENT_TEST_ID_RL_BAD = "comment-test-id-r&l-bad";
    public static final String COMMENT_TEST_ID_WP_GOOD = "comment-test-id-w&p-good";
    public static final String COMMENT_TEST_ID_WP_BAD = "comment-test-id-w&p-bad";
    public static final String GOOD_TEST_COMMENT = "Good test comment";
    public static final String BAD_TEST_COMMENT = "Bad test comment";

    @ChangeSet(order = "001", id="dropDb", author="chupaYchups", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id="insertAuthors", author= CHUPA_Y_CHUPS)
    public void insertAuthors(MongoOperations mongoTemplate) {
        Author authorPushkin = new Author(PUSHKIN_AUTHOR_NAME);
        authorPushkin.setId(PUSHKIN_AUTHOR_ID);
        mongoTemplate.save(authorPushkin);

        Author authorTolstoy = new Author(TOLSTOY_AUTHOR_NAME);
        authorTolstoy.setId(TOLSTOY_AUTHOR_ID);
        mongoTemplate.save(authorTolstoy);
    }

    @ChangeSet(order = "003", id="insertGenres", author=CHUPA_Y_CHUPS)
    public void insertGenres(MongoOperations mongoTemplate) {
        Genre novelGenre = new Genre(NOVEL_GENRE_NAME);
        novelGenre.setId(NOVEL_GENRE_ID);
        mongoTemplate.save(novelGenre);

        Genre storyGenre = new Genre(STORY_GENRE_NAME);
        storyGenre.setId(STORY_GENRE_ID);
        mongoTemplate.save(storyGenre);
    }


    @ChangeSet(order = "004", id="insertBooks", author=CHUPA_Y_CHUPS)
    public void insertBooks(MongoOperations mongoTemplate) {
        Book warAndPeaceBook = new Book(WAR_AND_PEACE_BOOK_NAME,
            mongoTemplate.findById(TOLSTOY_AUTHOR_ID, Author.class),
            mongoTemplate.findById(NOVEL_GENRE_ID, Genre.class));
        warAndPeaceBook.setId(WAR_AND_PEACE_BOOK_ID);
        mongoTemplate.save(warAndPeaceBook);

        Book ruslanAndLudmilaBook = new Book(RUSLAN_AND_LUDMILA_BOOK_NAME,
            mongoTemplate.findById(PUSHKIN_AUTHOR_ID, Author.class),
            mongoTemplate.findById(STORY_GENRE_ID, Genre.class));
        ruslanAndLudmilaBook.setId(RUSLAN_AND_LUDMILA_BOOK_ID);
        mongoTemplate.save(ruslanAndLudmilaBook);
    }

    @ChangeSet(order = "005", id="insertComments", author=CHUPA_Y_CHUPS)
    public void insertComments(MongoOperations mongoTemplate) {
        Book warAndPeaceBook = mongoTemplate.findById(WAR_AND_PEACE_BOOK_ID, Book.class);

        Comment warAndPeaceGoodComment = new Comment(GOOD_TEST_COMMENT, warAndPeaceBook);
        warAndPeaceGoodComment.setId(COMMENT_TEST_ID_WP_GOOD);
        mongoTemplate.save(warAndPeaceGoodComment);

        Comment warAndPeaceBadComment = new Comment(BAD_TEST_COMMENT, warAndPeaceBook);
        warAndPeaceBadComment.setId(COMMENT_TEST_ID_WP_BAD);
        mongoTemplate.save(warAndPeaceBadComment);

        Book ruslanAndLudmilaBook = mongoTemplate.findById(RUSLAN_AND_LUDMILA_BOOK_ID, Book.class);

        Comment ruslanAndLudmilaGoodComment = new Comment(GOOD_TEST_COMMENT, ruslanAndLudmilaBook);
        ruslanAndLudmilaGoodComment.setId(COMMENT_TEST_ID_RL_GOOD);
        mongoTemplate.save(ruslanAndLudmilaGoodComment);

        Comment ruslanAndLudmilaBadComment = new Comment(BAD_TEST_COMMENT, ruslanAndLudmilaBook);
        ruslanAndLudmilaBadComment.setId(COMMENT_TEST_ID_RL_BAD);
        mongoTemplate.save(ruslanAndLudmilaBadComment);
    }
}
