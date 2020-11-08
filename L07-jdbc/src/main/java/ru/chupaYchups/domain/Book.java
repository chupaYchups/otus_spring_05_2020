package ru.chupaYchups.domain;

import lombok.Data;
import java.util.Date;

@Data
public class Book {
    private final Long id;
    private final Date publishDate;
    private final String name;
    private final Author author;
    private final Genre genre;
}
