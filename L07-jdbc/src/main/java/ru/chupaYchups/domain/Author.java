package ru.chupaYchups.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "AUTHOR")
@Entity
public class Author {
    @Id
    private String id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "SECOND_NAME")
    private String secondName;
    @Column(name = "LAST_NAME")
    private String lastName;
}
