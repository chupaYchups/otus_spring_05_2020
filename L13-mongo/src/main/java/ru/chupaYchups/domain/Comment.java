package ru.chupaYchups.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("JpaDataSourceORMInspection")
@Data
@NoArgsConstructor
@Document(collection = "comments")
public class Comment {

    public Comment(String commentString, Book book) {
        this.commentString = commentString;
        this.book = book;
    }

    @Id
    private String id;

    private String commentString;

    private Book book;
}
