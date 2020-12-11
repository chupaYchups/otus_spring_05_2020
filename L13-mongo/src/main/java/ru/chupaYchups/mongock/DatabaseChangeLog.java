package ru.chupaYchups.mongock;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Genre;
import ru.chupaYchups.repository.AuthorRepository;
import ru.chupaYchups.repository.BookRepository;
import ru.chupaYchups.repository.GenreRepository;

@ChangeLog
public class DatabaseChangeLog {

    public static final String PUSHKIN_AUTHOR_NAME = "Pushkin";
    public static final String TOLSTOY_AUTHOR_NAME = "Tolstoy";
    public static final String DOSTOEVSKY_AUTHOR_NAME = "Dostoevsky";

    public static final String NOVEL_GENRE_NAME = "Novel";
    public static final String STORY_GENRE_NAME = "Story";
    public static final String DETECTIVE_GENRE_NAME = "Detective";

    public static final String WAR_AND_PEACE_BOOK_NAME = "War and peace";
    public static final String RUSLAN_AND_LUDMILA_BOOK_NAME = "Ruslan and Ludmila";
    public static final String CRIME_AND_PUNISHMENT_BOOK_NAME = "Crime and Punishment";

    public static final String CHUPA_Y_CHUPS = "chupaYchups";

    @ChangeSet(order = "001", id="dropDb", author="chupaYchups", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id="insertAuthors", author= CHUPA_Y_CHUPS)
    public void insertAuthors(AuthorRepository authorRepository) {
        authorRepository.save(new Author(PUSHKIN_AUTHOR_NAME));
        authorRepository.save(new Author(TOLSTOY_AUTHOR_NAME));
        authorRepository.save(new Author(DOSTOEVSKY_AUTHOR_NAME));
    }

    @ChangeSet(order = "003", id="insertGenres", author=CHUPA_Y_CHUPS)
    public void insertGenres(GenreRepository genreRepository) {
        genreRepository.save(new Genre(NOVEL_GENRE_NAME));
        genreRepository.save(new Genre(STORY_GENRE_NAME));
        genreRepository.save(new Genre(DETECTIVE_GENRE_NAME));
    }

    @ChangeSet(order = "004", id="insertBooks", author=CHUPA_Y_CHUPS)
    public void insertBooks(AuthorRepository authorRepository, GenreRepository genreRepository, BookRepository bookRepository) {
        bookRepository.save(new Book(WAR_AND_PEACE_BOOK_NAME,
            authorRepository.findByName(TOLSTOY_AUTHOR_NAME).orElseThrow(),
            genreRepository.findByName(NOVEL_GENRE_NAME).orElseThrow()));

        bookRepository.save(new Book(RUSLAN_AND_LUDMILA_BOOK_NAME,
            authorRepository.findByName(PUSHKIN_AUTHOR_NAME).orElseThrow(),
            genreRepository.findByName(STORY_GENRE_NAME).orElseThrow()));


        bookRepository.save(new Book(CRIME_AND_PUNISHMENT_BOOK_NAME,
            authorRepository.findByName(DOSTOEVSKY_AUTHOR_NAME).orElseThrow(),
            genreRepository.findByName(DETECTIVE_GENRE_NAME).orElseThrow()));
    }
}