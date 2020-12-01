package ru.chupaYchups.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Data
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
public class Book {

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AUTHOR_ID", nullable = false)
    private Author author;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GENRE_ID", nullable = false)
    private Genre genre;
}
