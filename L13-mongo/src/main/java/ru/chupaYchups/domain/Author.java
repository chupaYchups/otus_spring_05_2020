package ru.chupaYchups.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("JpaDataSourceORMInspection")
@Data
@NoArgsConstructor
@Document(collection = "authors")
public class Author {

    public Author(String name) {
        this.name = name;
    }

    @Id
    private String id;

    private String name;
}
