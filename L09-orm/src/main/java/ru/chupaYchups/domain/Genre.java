package ru.chupaYchups.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Data
@NoArgsConstructor
@Entity
@Table(name = "GENRE")
public class Genre {

    public Genre(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;
}
