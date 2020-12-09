package ru.chupaYchups.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("JpaDataSourceORMInspection")
@Data
@NoArgsConstructor
@Document
public class Genre {

    public Genre(String name) {
        this.name = name;
    }

    @Id
    private Long id;

    private String name;
}
