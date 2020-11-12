package ru.chupaYchups.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Data
@NoArgsConstructor
@Entity
@Table(name = "COMMENT")
public class Comment {

    public Comment(String commentString) {
        this.commentString = commentString;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "COMMENT_STRING")
    private String commentString;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BOOK_ID")
    private Book book;
}
