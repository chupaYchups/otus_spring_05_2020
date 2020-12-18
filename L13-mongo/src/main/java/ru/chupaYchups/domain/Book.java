package ru.chupaYchups.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@SuppressWarnings("JpaDataSourceORMInspection")
@Data
@NoArgsConstructor
@Document(collection = "books")
public class Book {

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    @Id
    private String id;

    private String name;

    @Field
    private Author author;

    @Field
    private Genre genre;

    @DBRef
    private List<Comment> comments;
}
