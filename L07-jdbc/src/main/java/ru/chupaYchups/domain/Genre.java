package ru.chupaYchups.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GENRE")
@Entity
public class Genre {
    @Id
    private String id;
    @Column(name = "GENRE_NAME")
    private String genreName;
}
