package ru.chupaYchups.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@SuppressWarnings("JpaDataSourceORMInspection")
@Data
@NoArgsConstructor
@Document
public class Book {

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    @Id
    private Long id;

    private String name;

    private Author author;

    private Genre genre;

    private List<Comment> comments;
}
