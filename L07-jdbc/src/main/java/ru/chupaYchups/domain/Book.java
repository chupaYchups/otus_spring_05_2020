package ru.chupaYchups.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Book {
    private Long id;
    private Date publishDate;
    private String bookName;
    private Author author;
    private Genre genre;
}
