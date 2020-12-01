package ru.chupaYchups.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Data
@NoArgsConstructor
@Entity
@Table(name = "AUTHOR")
public class Author {

    public Author(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;
}
