package ru.chupaYchups.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name="BOOK")
@Entity
public class Book {
    @Id
    private Long id;
    @Column(name = "PUBLISH_DATE")
    private Date publishDate;
    @Column(name = "BOOKNAME")
    private String bookName;
    @Column(name="AUTHOR_ID")
    @ManyToOne
    private Author author;
    @Column(name="GENRE_ID")
    @ManyToOne
    private Genre genre;
}
